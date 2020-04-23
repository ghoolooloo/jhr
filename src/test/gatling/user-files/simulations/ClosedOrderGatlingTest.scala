import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the ClosedOrder entity.
 */
class ClosedOrderGatlingTest extends Simulation {

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

    val scn = scenario("Test the ClosedOrder entity")
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
            exec(http("Get all closedOrders")
            .get("/api/closed-orders")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new closedOrder")
            .post("/api/closed-orders")
            .headers(headers_http_authenticated)
            .body(StringBody("""{
                "id":null
                , "sn":"SAMPLE_TEXT"
                , "memberName":"SAMPLE_TEXT"
                , "memberSN":"SAMPLE_TEXT"
                , "orderType":"TOGO"
                , "shopSN":"SAMPLE_TEXT"
                , "shopName":"SAMPLE_TEXT"
                , "priceTotal":"0"
                , "cardReduce":"0"
                , "cards":"SAMPLE_TEXT"
                , "rewardPointsReduce":"0"
                , "paymentTotal":"0"
                , "distributionPlatform":"MERCHANT"
                , "deliveryNo":"SAMPLE_TEXT"
                , "deliveryStatus":"0"
                , "deliveryDesc":"SAMPLE_TEXT"
                , "deliverier":"SAMPLE_TEXT"
                , "deliverierMobile":"SAMPLE_TEXT"
                , "deliveryDeductFee":"0"
                , "deliveryFee":"0"
                , "contact":"SAMPLE_TEXT"
                , "sex":"UNKNOWN"
                , "mobile":"SAMPLE_TEXT"
                , "country":"SAMPLE_TEXT"
                , "province":"SAMPLE_TEXT"
                , "city":"SAMPLE_TEXT"
                , "district":"SAMPLE_TEXT"
                , "offsetType":"MARS"
                , "longitude":null
                , "latitude":null
                , "address":"SAMPLE_TEXT"
                , "packingFee":"0"
                , "paymentMode":"WX"
                , "diningDate":"2020-01-01T00:00:00.000Z"
                , "remark":"SAMPLE_TEXT"
                , "status":"NEW"
                , "createdDate":"2020-01-01T00:00:00.000Z"
                , "paidDate":"2020-01-01T00:00:00.000Z"
                , "expiredDate":"2020-01-01T00:00:00.000Z"
                , "completedDate":"2020-01-01T00:00:00.000Z"
                , "exceptionDate":"2020-01-01T00:00:00.000Z"
                , "handler":"SAMPLE_TEXT"
                , "handledDate":"2020-01-01T00:00:00.000Z"
                , "handledDesc":"SAMPLE_TEXT"
                , "applicant":"SAMPLE_TEXT"
                , "appliedDate":"2020-01-01T00:00:00.000Z"
                , "refundDesc":"SAMPLE_TEXT"
                , "refundedBy":"SAMPLE_TEXT"
                , "refundedDate":"2020-01-01T00:00:00.000Z"
                , "refundAmount":"0"
                , "reply":"SAMPLE_TEXT"
                , "passed":null
                }""")).asJson
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_closedOrder_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created closedOrder")
                .get("${new_closedOrder_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created closedOrder")
            .delete("${new_closedOrder_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) during (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
