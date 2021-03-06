package archunitdemo;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;
import com.tngtech.archunit.library.GeneralCodingRules;

import static archunitdemo.CustomRules.*;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.GeneralCodingRules.USE_JAVA_UTIL_LOGGING;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "nl.craftsmen.archunitdemo")
public class ArchUnitJunit5Test {

    @ArchTest
    public static final ArchRule rule = Architectures.layeredArchitecture()
            .layer("Api").definedBy("..api..")
            .layer("Core").definedBy("..core..")
            .layer("Client").definedBy("..client..")

            .whereLayer("Api").mayNotBeAccessedByAnyLayer()
            .whereLayer("Core").mayOnlyBeAccessedByLayers("Api", "Client")
            .whereLayer("Client").mayNotBeAccessedByAnyLayer();

    @ArchTest
    public static final ArchRule packageRule = noClasses().that().resideInAPackage("..core..")
            .should().dependOnClassesThat().resideInAnyPackage("..api..", "..client..");

    @ArchTest
    public static final ArchRule onionModelRule = classes()
            .that()
            .resideInAPackage("..core..")
            .should()
            .onlyHaveDependentClassesThat()
            .resideInAnyPackage("..core..", "..api..", "..client..");

    @ArchTest
    public static final ArchRule rule4 = slices().matching("..api.(*service).model").should()
            .notDependOnEachOther();

    @ArchTest
    public static final ArchRule namingRule = classes()
            .that()
            .resideInAPackage("..api..")
            .and()
            .haveSimpleNameEndingWith("Controller")
            .should()
            .beAnnotatedWith("org.springframework.web.bind.annotation.RestController");

    @ArchTest
    public static final ArchRule customRule = noClasses().should(notHaveFieldsAnnotatedWithDeprecated);

}
