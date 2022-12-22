package com.stack.stackoverflow.restdocs;

import com.stack.stackoverflow.user.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

import javax.servlet.annotation.WebFilter;

@WebMvcTest
@MockBean
public class UserControllerRestDocsTest {
}
