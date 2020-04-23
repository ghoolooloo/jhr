import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the FoodSalesReport entity.
 */
class FoodSalesReportGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://localhost:8080"""

    val httpConf = http
        .baseUrl(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")
        .silentResources // Silence all resources like css or css so they don't clutter the results

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test the FoodSalesReport entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))
        ).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJson
        .check(header("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(2)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all foodSalesReports")
            .get("/api/food-sales-reports")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new foodSalesReport")
            .post("/api/food-sales-reports")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "day":"2020-01-01T00:00:00.000Z"
                , "shopSN":"SAMPLE_TEXT"
                , "shopName":"SAMPLE_TEXT"
                , "categorySN":"SAMPLE_TEXT"
                , "categoryName":"SAMPLE_TEXT"
                , "foodSN":"SAMPLE_TEXT"
                , "foodName":"SAMPLE_TEXT"
                , "cost":"0"
                , "price":"0"
                , "originalPrice":"0"
                , "salesQuantity":"0"
                , "salesAmount":"0"
                , "refundOrders":"0"
                , "refundAmount":"0"
                , "grossProfit":"0"
                , "initQuantity":"0"
                , "remainder":"0"
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_foodSalesReport_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created foodSalesReport")
                .get("${new_foodSalesReport_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created foodSalesReport")
            .delete("${new_foodSalesReport_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
