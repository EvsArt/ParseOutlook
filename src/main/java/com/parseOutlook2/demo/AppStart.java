package com.parseOutlook2.demo;

import com.parseOutlook2.demo.model.Member;
import com.parseOutlook2.demo.pages.LoginPage;
import com.parseOutlook2.demo.pages.ParsePage;
import com.parseOutlook2.demo.pages.ProfilePage;
import com.parseOutlook2.demo.repository.MemberRepository;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AppStart implements CommandLineRunner {

    final WebDriver driver;
    ParsePage parsePage;
    ProfilePage profilePage;
    MemberRepository memberRepository;

    public AppStart(WebDriver driver, ParsePage parsePage, ProfilePage profilePage, MemberRepository memberRepository) {
        this.driver = driver;
        this.parsePage = parsePage;
        this.profilePage = profilePage;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        final String LOGINPAGE = "https://login.live.com/login.srf?wa=wsignin1.0&rpsnv=13&ct=1667388895" +
                "&rver=7.0.6737.0&wp=MBI_SSL&wreply=https%3a%2f%2foutlook.live.com%2fowa%2f%3fnlp%3d1%26RpsCsrfState%3df1af1d00-8ef8-3794-194c-1033d11b4573&id=292841&aadredir=1" +
                "&CBCXT=out&lw=1&fl=dob%2cflname%2cwld&cobrandid=90015";
        final String LOGIN = "";
        final String PASSWORD = "";

        LoginPage loginPage = new LoginPage(driver, LOGINPAGE);
        loginPage.login(LOGIN, PASSWORD);

        List<Member> members = (List<Member>) memberRepository.findAllByNameIsNullOrderByEmail();

        for(Member member: members) {
            System.out.println("Обработка " + member);
            if(parsePage.getUser(member)) {
                Thread.sleep(2000);
                profilePage.updateMemberInfo(member);
            }
            System.out.println("Завершено " + member);

            driver.get("https://outlook.office365.com/mail/");
        }
    }

}
