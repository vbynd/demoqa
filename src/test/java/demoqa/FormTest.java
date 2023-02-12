package demoqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FormTest extends BaseTest{
    private WebElement firstName;
    private WebElement lastName;
    private WebElement email;
    private WebElement genderMale;
    private WebElement genderFemale;
    private WebElement genderOther;
    private WebElement mobile;
    private WebElement dateOfBirth;
    private WebElement subjects;
    private WebElement hobbieSports;
    private WebElement hobbieReading;
    private WebElement hobbieMusic;
    private WebElement picture;
    private WebElement currentAddress;
    private WebElement state;
    private WebElement city;
    private WebElement submit;

    // Сценарий - все поля заполнены валидными значениями
    @Test
    public void fillAllFieldsAndCheckResult() {
        String firstName = "Ivan";
        String lastName = "Minaev";
        String email = "minaevi@mail.ru";
        String mobile = "9008001122";
        String dayOfBirth = "12";
        String monthOfBith = "January";
        String yearOfBirth = "2000";
        String dateOfBirthAfterSubmit = dayOfBirth + " " + monthOfBith + "," + yearOfBirth;
        String subjects = "Physics";
        String filePath = "C:\\Users\\vbynd\\OneDrive\\Изображения\\200.png";
        String currentAddress = "Saratov";
        String state = "NCR";
        String city = "Delhi";

        driver.get("https://demoqa.com/automation-practice-form");
        initAllFields();
        fillFullForm(firstName, lastName, email, mobile,
                dayOfBirth, yearOfBirth, monthOfBith,
                subjects, filePath, currentAddress,
                state, city);
        genderMale.click();
        hobbieReading.click();
        hobbieMusic.click();
        hobbieSports.click();
        // На форме кнопка Submit не принимает метод Click() :)
        submit.sendKeys(Keys.ENTER);

        String xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody";
        Assertions.assertNotNull(driver.findElement(By.id("example-modal-sizes-title-lg")));
        Assertions.assertEquals(firstName + " " + lastName,
                driver.findElement(By.xpath(xpath + "/tr[1]/td[2]")).getText());
        Assertions.assertEquals(email,
                driver.findElement(By.xpath(xpath + "/tr[2]/td[2]")).getText());
        Assertions.assertEquals("Male",
                driver.findElement(By.xpath(xpath + "/tr[3]/td[2]")).getText());
        Assertions.assertEquals(mobile,
                driver.findElement(By.xpath(xpath + "/tr[4]/td[2]")).getText());
        Assertions.assertEquals(dateOfBirthAfterSubmit,
                driver.findElement(By.xpath(xpath + "/tr[5]/td[2]")).getText());
        Assertions.assertEquals(subjects,
                driver.findElement(By.xpath(xpath + "/tr[6]/td[2]")).getText());
        Assertions.assertEquals("Reading, Music, Sports",
                driver.findElement(By.xpath(xpath + "/tr[7]/td[2]")).getText());
        Assertions.assertTrue(filePath.endsWith(driver.findElement(By.xpath(xpath + "/tr[8]/td[2]")).getText()));
        Assertions.assertEquals(currentAddress,
                driver.findElement(By.xpath(xpath + "/tr[9]/td[2]")).getText());
        Assertions.assertEquals(state + " " + city,
                driver.findElement(By.xpath(xpath + "/tr[10]/td[2]")).getText());
    }

    // Сценарий - заполнено минимально допустимое количество полей
    @Test
    public void minimalAcceptableFormFilling() {
        String firstName = "Ivan";
        String lastName = "Minaev";
        String email = "minaevi@mail.ru";
        String mobile = "9008001122";
        String dayOfBirth = "12";
        String monthOfBirth = "January";
        String yearOfBirth = "2000";
        String dateOfBirthAfterSubmit = dayOfBirth + " " + monthOfBirth + "," + yearOfBirth;

        driver.get("https://demoqa.com/automation-practice-form");
        initAllFields();
        fillMinimumForm(firstName, lastName, email, mobile,
                dayOfBirth, yearOfBirth, monthOfBirth);
        genderFemale.click();
        // На форме кнопка Submit не принимает метод Click() :)
        submit.sendKeys(Keys.ENTER);

        String xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody";
        Assertions.assertNotNull(driver.findElement(By.id("example-modal-sizes-title-lg")));
        Assertions.assertEquals(firstName + " " + lastName,
                driver.findElement(By.xpath(xpath + "/tr[1]/td[2]")).getText());
        Assertions.assertEquals(email,
                driver.findElement(By.xpath(xpath + "/tr[2]/td[2]")).getText());
        Assertions.assertEquals("Female",
                driver.findElement(By.xpath(xpath + "/tr[3]/td[2]")).getText());
        Assertions.assertEquals(mobile,
                driver.findElement(By.xpath(xpath + "/tr[4]/td[2]")).getText());
        Assertions.assertEquals(dateOfBirthAfterSubmit,
                driver.findElement(By.xpath(xpath + "/tr[5]/td[2]")).getText());
        Assertions.assertEquals("",
                driver.findElement(By.xpath(xpath + "/tr[6]/td[2]")).getText());
        Assertions.assertEquals("",
                driver.findElement(By.xpath(xpath + "/tr[7]/td[2]")).getText());
        Assertions.assertTrue("".endsWith(driver.findElement(By.xpath(xpath + "/tr[8]/td[2]")).getText()));
        Assertions.assertEquals("",
                driver.findElement(By.xpath(xpath + "/tr[9]/td[2]")).getText());
        Assertions.assertEquals("",
                driver.findElement(By.xpath(xpath + "/tr[10]/td[2]")).getText());
    }

    // Сценарий - валидация почты
    @ParameterizedTest
    @ValueSource(strings = {"asd", "asd@", "asd.ru", "asd@asd"})
    public void emailValidation(String invalidEmail) {
        String firstName = "Ivan";
        String lastName = "Minaev";
        String email = invalidEmail;
        String mobile = "9008001122";
        String dayOfBirth = "12";
        String monthOfBith = "January";
        String yearOfBirth = "2000";
        String dateOfBitrh = dayOfBirth + " " + monthOfBith.substring(0, 3) + " " + yearOfBirth;
        String dateOfBirthAfterSubmit = dayOfBirth + " " + monthOfBith + "," + yearOfBirth;
        String subjects = "Physics";
        String filePath = "C:\\Users\\vbynd\\OneDrive\\Изображения\\200.png";
        String currentAddress = "Saratov";
        String state = "NCR";
        String city = "Delhi";

        driver.get("https://demoqa.com/automation-practice-form");
        initAllFields();
        fillFullForm(firstName, lastName, email, mobile,
                dayOfBirth, yearOfBirth, monthOfBith,
                subjects, filePath, currentAddress,
                state, city);
        genderMale.click();
        hobbieReading.click();
        hobbieMusic.click();
        hobbieSports.click();
        // На форме кнопка Submit не принимает метод Click() :)
        submit.sendKeys(Keys.ENTER);

        Assertions.assertEquals(0, driver.findElements(By.id("example-modal-sizes-title-lg")).size());
    }

    // Сценарий - валидация номера телефона
    @ParameterizedTest
    @ValueSource(strings = {"", "12345678901", "asdasdasda", "123456789"})
    public void phoneNumberValidation(String invalidPhone) {
        String firstName = "Ivan";
        String lastName = "Minaev";
        String email = "test@test.ru";
        String mobile = invalidPhone;
        String dayOfBirth = "12";
        String monthOfBith = "January";
        String yearOfBirth = "2000";
        String dateOfBitrh = dayOfBirth + " " + monthOfBith.substring(0, 3) + " " + yearOfBirth;
        String dateOfBirthAfterSubmit = dayOfBirth + " " + monthOfBith + "," + yearOfBirth;
        String subjects = "Physics";
        String filePath = "C:\\Users\\vbynd\\OneDrive\\Изображения\\200.png";
        String currentAddress = "Saratov";
        String state = "NCR";
        String city = "Delhi";

        driver.get("https://demoqa.com/automation-practice-form");
        initAllFields();
        fillFullForm(firstName, lastName, email, mobile,
                dayOfBirth, yearOfBirth, monthOfBith,
                subjects, filePath, currentAddress,
                state, city);
        genderMale.click();
        hobbieReading.click();
        hobbieMusic.click();
        hobbieSports.click();
        // На форме кнопка Submit не принимает метод Click() :)
        submit.sendKeys(Keys.ENTER);

        switch(invalidPhone) {
            case "12345678901":
                String xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody";
                Assertions.assertEquals(mobile.substring(0, 10),
                        driver.findElement(By.xpath(xpath + "/tr[4]/td[2]")).getText());
                break;
            default:
                Assertions.assertEquals(0, driver.findElements(By.id("example-modal-sizes-title-lg")).size());
                break;
        }
    }

    // Сценарий - валидация пола
    @Test
    public void genderNotSetValidation() {
        String firstName = "Ivan";
        String lastName = "Minaev";
        String email = "test@test.ru";
        String mobile = "1234567890";
        String dayOfBirth = "12";
        String monthOfBith = "January";
        String yearOfBirth = "2000";
        String dateOfBitrh = dayOfBirth + " " + monthOfBith.substring(0, 3) + " " + yearOfBirth;
        String dateOfBirthAfterSubmit = dayOfBirth + " " + monthOfBith + "," + yearOfBirth;
        String subjects = "Physics";
        String filePath = "C:\\Users\\vbynd\\OneDrive\\Изображения\\200.png";
        String currentAddress = "Saratov";
        String state = "NCR";
        String city = "Delhi";

        driver.get("https://demoqa.com/automation-practice-form");
        initAllFields();
        fillFullForm(firstName, lastName, email, mobile,
                dayOfBirth, yearOfBirth, monthOfBith,
                subjects, filePath, currentAddress,
                state, city);

        hobbieReading.click();
        hobbieMusic.click();
        hobbieSports.click();
        // На форме кнопка Submit не принимает метод Click() :)
        submit.sendKeys(Keys.ENTER);

        Assertions.assertEquals(0, driver.findElements(By.id("example-modal-sizes-title-lg")).size());
    }

    // Сценарий - не указано имя
    @Test
    public void firstNameValidation() {
        String lastName = "Minaev";
        String email = "invalidEmail@mail.ru";
        String mobile = "9008001122";
        String dayOfBirth = "12";
        String monthOfBith = "January";
        String yearOfBirth = "2000";

        driver.get("https://demoqa.com/automation-practice-form");
        initAllFields();
        fillFormWithoutFisrtName(lastName, email, mobile,
                dayOfBirth, yearOfBirth, monthOfBith);
        genderMale.click();
        // На форме кнопка Submit не принимает метод Click() :)
        submit.sendKeys(Keys.ENTER);

        Assertions.assertEquals(0, driver.findElements(By.id("example-modal-sizes-title-lg")).size());
    }

    // Сценарий - не указана фамилия
    @Test
    public void lastNameValidation() {
        String fisrtName = "Ivan";
        String email = "invalidEmail@mail.ru";
        String mobile = "9008001122";
        String dayOfBirth = "12";
        String monthOfBith = "January";
        String yearOfBirth = "2000";

        driver.get("https://demoqa.com/automation-practice-form");
        initAllFields();
        fillFormWithoutLastName(fisrtName, email, mobile,
                dayOfBirth, yearOfBirth, monthOfBith);
        genderMale.click();
        // На форме кнопка Submit не принимает метод Click() :)
        submit.sendKeys(Keys.ENTER);

        Assertions.assertEquals(0, driver.findElements(By.id("example-modal-sizes-title-lg")).size());
    }

    private void initAllFields() {
        firstName = driver.findElement(By.id("firstName"));
        lastName = driver.findElement(By.id("lastName"));
        email = driver.findElement(By.id("userEmail"));
        genderMale = driver.findElement(By.cssSelector("[for='gender-radio-1']"));
        genderFemale = driver.findElement(By.cssSelector("[for='gender-radio-2']"));
        genderOther = driver.findElement(By.cssSelector("[for='gender-radio-3']"));
        mobile = driver.findElement(By.id("userNumber"));
        dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        subjects = driver.findElement(By.id("subjectsInput"));
        hobbieSports = driver.findElement(By.cssSelector("[for='hobbies-checkbox-1']"));
        hobbieReading = driver.findElement(By.cssSelector("[for='hobbies-checkbox-2']"));
        hobbieMusic = driver.findElement(By.cssSelector("[for='hobbies-checkbox-3']"));
        picture = driver.findElement(By.id("uploadPicture"));
        currentAddress = driver.findElement(By.id("currentAddress"));
        state = driver.findElement(By.id("react-select-3-input"));
        city = driver.findElement(By.id("react-select-4-input"));
        submit = driver.findElement(By.id("submit"));
    }

    private void fillFullForm(String firstName, String lastName, String email, String mobile,
                              String dBirth, String yBirth, String mBirth,
                              String subjects, String filePath, String currentAddress,
                              String state, String city) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.email.sendKeys(email);
        this.mobile.sendKeys(mobile);
        this.dateOfBirth.click();
        selectDate(dBirth, mBirth, yBirth);
        this.subjects.sendKeys(subjects);
        this.subjects.sendKeys(Keys.ENTER);
        this.picture.sendKeys(filePath);
        this.currentAddress.sendKeys(currentAddress);
        this.state.sendKeys(state);
        this.state.sendKeys(Keys.ENTER);
        this.city.sendKeys(city);
        this.city.sendKeys(Keys.ENTER);
    }

    private void fillMinimumForm(String firstName, String lastName, String email, String mobile,
                              String dBirth, String yBirth, String mBirth) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.email.sendKeys(email);
        this.mobile.sendKeys(mobile);
        this.dateOfBirth.click();
        selectDate(dBirth, mBirth, yBirth);
    }

    private void fillFormWithoutFisrtName(String lastName, String email, String mobile,
                                 String dBirth, String yBirth, String mBirth) {
        this.lastName.sendKeys(lastName);
        this.email.sendKeys(email);
        this.mobile.sendKeys(mobile);
        this.dateOfBirth.click();
        selectDate(dBirth, mBirth, yBirth);
    }

    private void fillFormWithoutLastName(String firstName, String email, String mobile,
                                          String dBirth, String yBirth, String mBirth) {
        this.firstName.sendKeys(firstName);
        this.email.sendKeys(email);
        this.mobile.sendKeys(mobile);
        this.dateOfBirth.click();
        selectDate(dBirth, mBirth, yBirth);
    }


    /*
    * Пришлось дописать ручное заполнение датапикера, т.к. не получается отправить в него значение нормальным способом
    *
    * С таким заполнением метод может выбрать не ту дату, которую мы выбрали, т.к. в календаре отображаются конец предыдущего
    * и начало следующего месяца. Не успел проработать этот момент(
    * */

    private void selectDate(String day, String month, String year) {
        Select m = new Select(driver.findElement(By.className("react-datepicker__month-select")));
        Select y = new Select(driver.findElement(By.className("react-datepicker__year-select")));
        m.selectByVisibleText(month);
        y.selectByVisibleText(year);

        WebElement d = driver.findElement(By.cssSelector(".react-datepicker__day.react-datepicker__day--0"+ day));
        d.click();
    }
}
