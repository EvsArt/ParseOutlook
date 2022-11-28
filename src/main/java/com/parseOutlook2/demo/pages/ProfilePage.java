package com.parseOutlook2.demo.pages;

import com.parseOutlook2.demo.model.Member;
import com.parseOutlook2.demo.model.Photo;
import com.parseOutlook2.demo.repository.MemberRepository;
import com.parseOutlook2.demo.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;

@Controller
@RequiredArgsConstructor
public class ProfilePage {

    public static long photoId = 1;

    Robot robot;

    WebDriver driver;
    WebDriverWait wait;
    PhotoRepository photoRepository;
    MemberRepository memberRepository;

    @Autowired
    public ProfilePage(WebDriver driver, PhotoRepository photoRepository, MemberRepository memberRepository) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(600));
        this.photoRepository = photoRepository;
        this.memberRepository = memberRepository;
    }


    @FindBy(css = ".-gCHo")
    private WebElement role;

    @FindBy(css = "._9unPA")
    private WebElement fullName;

    @FindBy(css = "div.list-408:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > button:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > span:nth-child(2)")
    private WebElement email;



    @SneakyThrows
    public void updateMemberInfo(Member member){

        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        String photoPath;
        String stringFullName = fullName.getAttribute("title");
        String[] masFullName = stringFullName.split(" ");
        if(masFullName.length > 2) {
            member.setSurname(masFullName[0]);
            member.setName(masFullName[1]);
            member.setPatronymic(masFullName[2]);
        }else if(masFullName.length == 2){
            member.setSurname(masFullName[0]);
            member.setName(masFullName[1]);
        }else if(masFullName.length == 1){
            member.setName(masFullName[0]);
        }else member.setName("NONE");


        try {
            member.setRole(role.getText());
        } catch (NoSuchElementException ignore){}

        photoPath = "https://outlook.office365.com/profile/v1.0/users/" + member.getEmail() + "/image/resize(width=120,height=120,allowResizeUp=true)";

        driver.get(photoPath);

        Thread.sleep(500);

        Robot robot = new Robot();
        String format = "png";
        String name = "/home/artevseev/Desktop/Студенты/" + member.getEmail().split("@")[0] + "." + format;

        Rectangle rectagle = new Rectangle(623, 361, 120, 120);
        BufferedImage image = robot.createScreenCapture(rectagle);

        ImageIO.write(image, format, new File(name));

        Photo photo = new Photo(member.getEmail().split("@")[0] + ".png", member.getEmail());

        photoRepository.save(photo);
        memberRepository.save(member);
    }


//    private String findPhotoPathByFullName(String fullname){
//        List<WebElement> webElements = driver.findElements(By.className("ms-Image-image--portrait"));
//        for(WebElement elem: webElements){
//            if(elem.getAttribute("alt").contains(fullname))
//                    return elem.getAttribute("src");
//        }
//        throw new NoSuchElementException("Фотография не найдена!");
//    }
}
