package nl.craftsmen.archunitdemo;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

public class CustomRules {

    public static DescribedPredicate<JavaClass> haveAFieldAnnotatedWithDeprecated =
            new DescribedPredicate<JavaClass>("have a field annotated with @Deprecated") {
                @Override
                public boolean apply(JavaClass input) {
                    boolean someFieldAnnotatedWithPayload = true;
                    return someFieldAnnotatedWithPayload;
                }
            };

    public static ArchCondition<JavaClass> notHaveFieldsAnnotatedWithDeprecated =
            new ArchCondition<JavaClass>("should not have a field annotated with @Deprecated") {
                @Override
                public void check(JavaClass item, ConditionEvents events) {
                    //if class has a deprecated field
                    events.add(SimpleConditionEvent.violated(item, "should not have a field annotated with @Deprecated"));
                }
            };

}
