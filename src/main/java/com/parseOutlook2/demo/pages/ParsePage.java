package com.parseOutlook2.demo.pages;

import com.parseOutlook2.demo.model.Member;
import com.parseOutlook2.demo.repository.MemberRepository;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
public class ParsePage {

    WebDriver driver;
    WebDriverWait wait;
    MemberRepository memberRepository;

    public ParsePage(WebDriver driver, MemberRepository memberRepository) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.memberRepository = memberRepository;
    }

    @FindBy(id = "searchBoxColumnContainerId")
    private WebElement searchClick;

    @FindBy(id = "topSearchInput")
    private WebElement searchField;

    @FindBy(xpath = "/html/body/div[2]/div/div[1]/div/div[1]/div[2]/div/div/div/div/div[1]/div[2]/div/div/div/div/div[1]/button")
    private WebElement searchBtn;

    @FindBy(className = "rclHC")
    private WebElement clickInput;

    @FindBy(className = "kDMk7")
    private WebElement getProfile;

    @SneakyThrows
    public Boolean getUser(Member member){
        wait.until(ExpectedConditions.elementToBeClickable(By.id("topSearchInput")));

        int schet = 1;
        while (true) {
            try {
                if(schet++ == 3) {
                    member.setName("NONE");
                    memberRepository.save(member);
                    return false;
                }
                clickInput.click();
                searchField.clear();
                searchField.sendKeys(member.getEmail());

                wait.until(ExpectedConditions.elementToBeClickable(By.className("kDMk7")));
                getProfile.click();
                break;
            }catch (TimeoutException ignore){}
        }
        return true;
    }




}
