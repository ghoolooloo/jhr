package jo.jhr;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("jo.jhr");

        noClasses()
            .that()
                .resideInAnyPackage("jo.jhr.service..")
            .or()
                .resideInAnyPackage("jo.jhr.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..jo.jhr.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
