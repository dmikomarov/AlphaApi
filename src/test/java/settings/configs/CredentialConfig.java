package settings.configs;

import static org.aeonbits.owner.Config.*;

@LoadPolicy(LoadType.MERGE)
@Sources({
        "system:properties",
        "classpath:credential.properties"
})
public interface CredentialConfig {


    @Key("selenide.login")
    String getSelenideLogin();

    @Key("selenide.password")
    String getSelenidePassword();
}
