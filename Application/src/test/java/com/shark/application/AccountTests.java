package com.shark.application;

import com.google.gson.Gson;
import com.shark.application.configuration.security.SecurityConfiguration;
import com.shark.application.service.account.GetAccountMenuListService;
import com.shark.application.service.account.SearchAccountListService;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class AccountTests {

    private Gson gson = new Gson();

    @Autowired
    private SearchAccountListService searchAccountListService;

    @Test
    public void testSearchAccountList() {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(SearchAccountListService.INPUT_PAGE_NUMBER, "0");
        parameters.put(SearchAccountListService.INPUT_PAGE_SIZE, "20");
        parameters.put(SearchAccountListService.INPUT_KEYWORD, "ro");
        printResult(searchAccountListService.request("1", parameters));
    }

    @Autowired
    private GetAccountMenuListService getAccountMenuListService;

    @Test
    public void testGetAccountMenuList() {
        String jwt = "base_eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNTUzNzQzNDY1fQ.gnmmYph0AeHHc6bMzDjfFwVsXMLFzTnnDoSrptXmvX74GGfXS718RvEOhP2N2ODYX0CP_EQM6N_XSZsS_tILIg";
        String account = Jwts.parser()
                .setSigningKey(SecurityConfiguration.SECRET.getBytes())
                .parseClaimsJws(jwt.replace(SecurityConfiguration.TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        HashMap<String, String> parameters = new HashMap<>();
        System.out.println("testGetAccountMenuList account: " + account);
        printResult(getAccountMenuListService.request(account , parameters));
    }

    private void printResult(Object result) {
        System.out.println("json result: " + gson.toJson(result));
    }

}
