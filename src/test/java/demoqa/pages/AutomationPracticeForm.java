package demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AutomationPracticeForm {
    private WebDriver driver;

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
    private WebElement submittedName;
    private WebElement submittedEmail;
    private WebElement submittedGender;
    private WebElement submittedMobile;
    private WebElement submittedBirth;
    private WebElement submittedSubjects;
    private WebElement submittedHobbies;
    private WebElement submittedPicture;
    private WebElement submittedAddress;
    private WebElement submittedStateAndCity;

    public AutomationPracticeForm(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSubmittedName() {
        return submittedName;
    }

    public WebElement getSubmittedEmail() {
        return submittedEmail;
    }

    public WebElement getSubmittedGender() {
        return submittedGender;
    }

    public WebElement getSubmittedMobile() {
        return submittedMobile;
    }

    public WebElement getSubmittedBirth() {
        return submittedBirth;
    }

    public WebElement getSubmittedSubjects() {
        return submittedSubjects;
    }

    public WebElement getSubmittedHobbies() {
        return submittedHobbies;
    }

    public WebElement getSubmittedPicture() {
        return submittedPicture;
    }

    public WebElement getSubmittedAddress() {
        return submittedAddress;
    }

    public WebElement getSubmittedStateAndCity() {
        return submittedStateAndCity;
    }

    public void open() {
        driver.get("https://demoqa.com/automation-practice-form");
        initAllFields();
    }

    public boolean at() {
        return driver.getTitle().equals("DEMOQA") ? true : false;
    }

    public void fillFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    public void fillEmail(String email) {
        this.email.sendKeys(email);
    }

    public void fillMobile(String mobile) {
        this.mobile.sendKeys(mobile);
    }

    public void fillSubject(String subjects) {
        this.subjects.sendKeys(subjects);
        this.subjects.sendKeys(Keys.ENTER);
    }

    public void fillPicture(String filePath) {
        this.picture.sendKeys(filePath);
    }

    public void fillCurrentAddress(String currentAddress) {
        this.currentAddress.sendKeys(currentAddress);
    }

    public void fillState(String state) {
        this.state.sendKeys(state);
        this.state.sendKeys(Keys.ENTER);
    }

    public void fillCity(String city) {
        this.city.sendKeys(city);
        this.city.sendKeys(Keys.ENTER);
    }

    public void fillDate(String day, String month, String year) {
        this.dateOfBirth.click();

        Select m = new Select(driver.findElement(By.className("react-datepicker__month-select")));
        Select y = new Select(driver.findElement(By.className("react-datepicker__year-select")));
        m.selectByVisibleText(month);
        y.selectByVisibleText(year);

        WebElement d = driver.findElement(By.cssSelector(".react-datepicker__day.react-datepicker__day--0"+ day));
        d.click();
    }

    public void fillGender(String gender) {
        if (gender == "Male") {
            this.genderMale.click();
        } else if (gender == "Female") {
            this.genderFemale.click();
        } else {
            this.genderOther.click();
        }
    }

    public void fillHobbieSports() {
        this.hobbieSports.click();
    }

    public void fillHobbieMusic() {
        this.hobbieMusic.click();
    }

    public void fillHobbieReading() {
        this.hobbieReading.click();
    }

    public void submit() {
        this.submit.sendKeys(Keys.ENTER);
        initSubmittedForm();
    }

    public boolean modalIsOpen() {
        try {
            driver.findElement(By.className("modal-content"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void initAllFields() {
        this.firstName = driver.findElement(By.id("firstName"));
        this.lastName = driver.findElement(By.id("lastName"));
        this.email = driver.findElement(By.id("userEmail"));
        this.genderMale = driver.findElement(By.cssSelector("[for='gender-radio-1']"));
        this.genderFemale = driver.findElement(By.cssSelector("[for='gender-radio-2']"));
        this.genderOther = driver.findElement(By.cssSelector("[for='gender-radio-3']"));
        this.mobile = driver.findElement(By.id("userNumber"));
        this.dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        this.subjects = driver.findElement(By.id("subjectsInput"));
        this.hobbieSports = driver.findElement(By.cssSelector("[for='hobbies-checkbox-1']"));
        this.hobbieReading = driver.findElement(By.cssSelector("[for='hobbies-checkbox-2']"));
        this.hobbieMusic = driver.findElement(By.cssSelector("[for='hobbies-checkbox-3']"));
        this.picture = driver.findElement(By.id("uploadPicture"));
        this.currentAddress = driver.findElement(By.id("currentAddress"));
        this.state = driver.findElement(By.id("react-select-3-input"));
        this.city = driver.findElement(By.id("react-select-4-input"));
        this.submit = driver.findElement(By.id("submit"));
    }

    private void initSubmittedForm() {
        if (modalIsOpen()) {
            String xpath = "//table//tbody";
            this.submittedName = driver.findElement(By.xpath(xpath + "/tr[1]/td[2]"));
            this.submittedEmail = driver.findElement(By.xpath(xpath + "/tr[2]/td[2]"));
            this.submittedGender = driver.findElement(By.xpath(xpath + "/tr[3]/td[2]"));
            this.submittedMobile = driver.findElement(By.xpath(xpath + "/tr[4]/td[2]"));
            this.submittedBirth = driver.findElement(By.xpath(xpath + "/tr[5]/td[2]"));
            this.submittedSubjects = driver.findElement(By.xpath(xpath + "/tr[6]/td[2]"));
            this.submittedHobbies = driver.findElement(By.xpath(xpath + "/tr[7]/td[2]"));
            this.submittedPicture = driver.findElement(By.xpath(xpath + "/tr[8]/td[2]"));
            this.submittedAddress = driver.findElement(By.xpath(xpath + "/tr[9]/td[2]"));
            this.submittedStateAndCity = driver.findElement(By.xpath(xpath + "/tr[10]/td[2]"));
        }
    }
}
