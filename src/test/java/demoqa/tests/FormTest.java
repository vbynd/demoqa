package demoqa.tests;

import demoqa.pages.AutomationPracticeForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;

public class FormTest extends BaseTest{
    private final String pictureFilePath = "./src/test/resources/200.png";

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
        String currentAddress = "Saratov";
        String state = "NCR";
        String city = "Delhi";

        AutomationPracticeForm practicePage = new AutomationPracticeForm(driver);
        practicePage.open();
        practicePage.at();
        practicePage.fillFirstName(firstName);
        practicePage.fillLastName(lastName);
        practicePage.fillEmail(email);
        practicePage.fillMobile(mobile);
        practicePage.fillDate(dayOfBirth, monthOfBith, yearOfBirth);
        practicePage.fillSubject(subjects);
        practicePage.fillPicture(new File(pictureFilePath).getAbsolutePath());
        practicePage.fillCurrentAddress(currentAddress);
        practicePage.fillState(state);
        practicePage.fillCity(city);
        practicePage.fillGender("Male");
        practicePage.fillHobbieReading();
        practicePage.fillHobbieMusic();
        practicePage.fillHobbieSports();
        practicePage.submit();

        Assertions.assertTrue(practicePage.modalIsOpen());
        Assertions.assertEquals(firstName + " " + lastName, practicePage.getSubmittedName().getText());
        Assertions.assertEquals(email, practicePage.getSubmittedEmail().getText());
        Assertions.assertEquals("Male", practicePage.getSubmittedGender().getText());
        Assertions.assertEquals(mobile, practicePage.getSubmittedMobile().getText());
        Assertions.assertEquals(dateOfBirthAfterSubmit, practicePage.getSubmittedBirth().getText());
        Assertions.assertEquals(subjects, practicePage.getSubmittedSubjects().getText());
        Assertions.assertEquals("Reading, Music, Sports", practicePage.getSubmittedHobbies().getText());
        Assertions.assertTrue(pictureFilePath.endsWith(practicePage.getSubmittedPicture().getText()));
        Assertions.assertEquals(currentAddress, practicePage.getSubmittedAddress().getText());
        Assertions.assertEquals(state + " " + city, practicePage.getSubmittedStateAndCity().getText());
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

        AutomationPracticeForm practicePage = new AutomationPracticeForm(driver);
        practicePage.open();
        practicePage.at();
        practicePage.fillFirstName(firstName);
        practicePage.fillLastName(lastName);
        practicePage.fillEmail(email);
        practicePage.fillMobile(mobile);
        practicePage.fillDate(dayOfBirth, monthOfBirth, yearOfBirth);
        practicePage.fillGender("Female");
        practicePage.submit();

        Assertions.assertTrue(practicePage.modalIsOpen());
        Assertions.assertEquals(firstName + " " + lastName, practicePage.getSubmittedName().getText());
        Assertions.assertEquals(email, practicePage.getSubmittedEmail().getText());
        Assertions.assertEquals("Female", practicePage.getSubmittedGender().getText());
        Assertions.assertEquals(mobile, practicePage.getSubmittedMobile().getText());
        Assertions.assertEquals(dateOfBirthAfterSubmit, practicePage.getSubmittedBirth().getText());
        Assertions.assertEquals("", practicePage.getSubmittedSubjects().getText());
        Assertions.assertEquals("", practicePage.getSubmittedHobbies().getText());
        Assertions.assertEquals("", practicePage.getSubmittedPicture().getText());
        Assertions.assertEquals("", practicePage.getSubmittedAddress().getText());
        Assertions.assertEquals("", practicePage.getSubmittedStateAndCity().getText());
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

        AutomationPracticeForm practicePage = new AutomationPracticeForm(driver);
        practicePage.open();
        practicePage.at();
        practicePage.fillFirstName(firstName);
        practicePage.fillLastName(lastName);
        practicePage.fillEmail(email);
        practicePage.fillMobile(mobile);
        practicePage.fillDate(dayOfBirth, monthOfBith, yearOfBirth);
        practicePage.fillGender("Female");
        practicePage.submit();

        Assertions.assertFalse(practicePage.modalIsOpen());
    }

    // Сценарий - валидация номера телефона
    @ParameterizedTest
    @ValueSource(strings = {"", "asdasdasda", "123456789"})
    public void phoneNumberValidation(String invalidPhone) {
        String firstName = "Ivan";
        String lastName = "Minaev";
        String email = "test@test.ru";
        String mobile = invalidPhone;
        String dayOfBirth = "12";
        String monthOfBith = "January";
        String yearOfBirth = "2000";

        AutomationPracticeForm practicePage = new AutomationPracticeForm(driver);
        practicePage.open();
        practicePage.at();
        practicePage.fillFirstName(firstName);
        practicePage.fillLastName(lastName);
        practicePage.fillEmail(email);
        practicePage.fillMobile(mobile);
        practicePage.fillDate(dayOfBirth, monthOfBith, yearOfBirth);
        practicePage.fillGender("Female");
        practicePage.submit();

        Assertions.assertFalse(practicePage.modalIsOpen());
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

        AutomationPracticeForm practicePage = new AutomationPracticeForm(driver);
        practicePage.open();
        practicePage.at();
        practicePage.fillFirstName(firstName);
        practicePage.fillLastName(lastName);
        practicePage.fillEmail(email);
        practicePage.fillMobile(mobile);
        practicePage.fillDate(dayOfBirth, monthOfBith, yearOfBirth);
        practicePage.submit();

        Assertions.assertFalse(practicePage.modalIsOpen());
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

        AutomationPracticeForm practicePage = new AutomationPracticeForm(driver);
        practicePage.open();
        practicePage.at();
        practicePage.fillLastName(lastName);
        practicePage.fillEmail(email);
        practicePage.fillMobile(mobile);
        practicePage.fillDate(dayOfBirth, monthOfBith, yearOfBirth);
        practicePage.fillGender("Male");
        practicePage.fillHobbieReading();
        practicePage.fillHobbieMusic();
        practicePage.fillHobbieSports();
        practicePage.submit();

        Assertions.assertFalse(practicePage.modalIsOpen());
    }

    // Сценарий - не указана фамилия
    @Test
    public void lastNameValidation() {
        String firstName = "Ivan";
        String email = "invalidEmail@mail.ru";
        String mobile = "9008001122";
        String dayOfBirth = "12";
        String monthOfBith = "January";
        String yearOfBirth = "2000";

        AutomationPracticeForm practicePage = new AutomationPracticeForm(driver);
        practicePage.open();
        practicePage.at();
        practicePage.fillFirstName(firstName);
        practicePage.fillEmail(email);
        practicePage.fillMobile(mobile);
        practicePage.fillDate(dayOfBirth, monthOfBith, yearOfBirth);
        practicePage.fillGender("Male");
        practicePage.fillHobbieReading();
        practicePage.fillHobbieMusic();
        practicePage.fillHobbieSports();
        practicePage.submit();

        Assertions.assertFalse(practicePage.modalIsOpen());
    }
}
