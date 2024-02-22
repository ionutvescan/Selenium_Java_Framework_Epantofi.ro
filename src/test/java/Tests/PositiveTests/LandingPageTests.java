package Tests.PositiveTests;

import Tests.TestComponents.BaseTest;
import ionutvescan.PageObjects.LandingPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LandingPageTests extends BaseTest {
    @Test(dataProvider = "getData")
    public void verifyCategoryLinks(HashMap<String,String> category) throws IOException {
        LandingPage landingPage = launchApplication();
        landingPage.acceptCookies();
        landingPage.selectCategory(category.get("category"));
        landingPage.verifyCategoryLinks(category.get("category"));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(
                "C:\\Users\\Ionut\\IdeaProjects\\CommerceWebApp\\src\\test\\java\\Tests\\Data\\Categories.json");
        return new Object[][] {{data.get(2)}};
    }
}
