package nl.craftsmen.archunitdemo;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class ArchUnitTest {

    @Test
    public void layerTest() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.craftsmen.archunitdemo");
        ArchRule rule =layeredArchitecture()
                .layer("Api").definedBy("..api..")
                .layer("Core").definedBy("..core..")
                .layer("Client").definedBy("..client..")

                .whereLayer("Api").mayNotBeAccessedByAnyLayer()
                .whereLayer("Core").mayOnlyBeAccessedByLayers("Api", "Client")
                .whereLayer("Client").mayNotBeAccessedByAnyLayer();
        rule.check(importedClasses);


    }
}
