package archunitdemo;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

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
    public static final ArchRule rule2 = ArchRuleDefinition.noClasses().that().resideInAPackage("..core..")
            .should().dependOnClassesThat().resideInAPackage("..api..");

    @ArchTest
    public static final ArchRule rule3 = ArchRuleDefinition.classes().that().resideInAPackage("..core..")
            .should().onlyHaveDependentClassesThat().resideInAnyPackage("..core..", "..api..", "..client..");
}
