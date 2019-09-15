package ca.khazri.superHero.config;


 import ca.khazri.superHero.controller.MissionController;
 import ca.khazri.superHero.model.Mission;
 import ca.khazri.superHero.service.MissionService;
 import org.junit.Before;
 import org.junit.Test;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.mockito.MockitoAnnotations;
 import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
 import org.springframework.http.MediaType;
 import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 import org.springframework.security.test.context.support.WithMockUser;
 import org.springframework.test.web.servlet.MockMvc;
 import org.springframework.test.web.servlet.setup.MockMvcBuilders;

 import static org.hamcrest.core.Is.is;
 import static org.mockito.Mockito.*;
 import static org.mockito.Mockito.verifyNoMoreInteractions;
 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
 import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.boot.test.mock.mockito.MockBean;
 import org.springframework.security.test.context.support.WithMockUser;
 import org.springframework.test.context.ActiveProfiles;
 import org.springframework.test.context.junit4.SpringRunner;
 import org.springframework.test.web.servlet.MockMvc;

 import java.math.BigDecimal;
 import java.util.Optional;

 import static org.hamcrest.Matchers.is;
 import static org.mockito.Mockito.when;
 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
 import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
 import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
    public class SecurityConfigTest extends WebSecurityConfigurerAdapter {

    private MockMvc mockMvc;

    @Mock
    private MissionService missionService;

    @InjectMocks
    private MissionController missionController;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(missionController).build();
    }

    @WithMockUser("ADMIN")
    @Test
    public void deleteMission() throws Exception {
        Mission mission = new Mission(1L, "mission", false, false);
        mockMvc.perform(delete("/api/missions/{missionId}", mission.getId()))
                .andDo(print())
                .andExpect(status().isOk ());

    }
    
}