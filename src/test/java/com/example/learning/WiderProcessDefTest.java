package com.example.learning;

import com.example.learning.camunda.delegate.GenerateRandomNumber;
import com.example.learning.camunda.delegate.MakeGuessDelegate;
import com.example.learning.model.CamundaVariableNames;
import com.example.learning.service.NumberGuessingService;
import com.example.learning.service.RandomNumberService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.rest.util.EngineUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.mockito.DelegateExpressions;
import org.camunda.bpm.extension.mockito.ProcessExpressions;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.hibernate.validator.constraints.ModCheck;
import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.powermock.modules.junit4.PowerMockRunner;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.camunda.bpm.extension.mockito.DelegateExpressions.autoMock;

/**
 * Trying to follow 1 in https://camunda.com/best-practices/testing-process-definitions/
 *
 * In this test class spring will not be instantiated so we have to mock dependencies needed
 *
 */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({EngineUtil.class})
//@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
public class WiderProcessDefTest {

//    @InjectMocks
//    @Rule // creates an in memory processing engine on setup and teardown
//    public ProcessEngineRule processEngineRule = new ProcessEngineRule();

//    private NumberGuessingService numberGuessingService;
//    private RandomNumberService randomNumberService;
//    private MakeGuessDelegate makeGuessDelegate;
//    private GenerateRandomNumber generateRandomNumber;
//
//    private RuntimeService runtimeService;

    @Autowired
    private RuntimeService runtimeService;

    @Before
    public void setup() {
//        init(processEngineRule.getProcessEngine());
//        numberGuessingService = Mockito.mock(NumberGuessingService.class);
//        Mockito.when(numberGuessingService.guessNewNumber(null, null)).thenReturn(100);
//        randomNumberService = Mockito.mock(RandomNumberService.class);
//        Mockito.when(randomNumberService.getRandomNumber()).thenReturn(100);
//        Mocks.register("numberGuessingService", numberGuessingService);
//        Mocks.register("randomNumberService", randomNumberService);
//        makeGuessDelegate = new MakeGuessDelegate();
//        ReflectionTestUtils.setField(makeGuessDelegate, "numberGuessingService", numberGuessingService);
//        Mocks.register("makeGuessDelegate", makeGuessDelegate);
//        generateRandomNumber = new GenerateRandomNumber();
//        ReflectionTestUtils.setField(generateRandomNumber, "randomNumberService", randomNumberService);
//        Mocks.register("generateRandomNumber", generateRandomNumber);
    }

    @After
    public void teardown() {
        Mocks.reset();
    }

    @Test
    @Deployment(resources = "process.bpmn")
    public void testNumberGuessCorrect() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("number_guessing");

        // now it should just run straight through without going to guess higher or guess lower

        // then
        assertThat(processInstance)
                .hasPassed("get_random_number")
                .hasPassed("make_guess")
                .hasPassed("check_number")
                .hasPassed("finish_task")
                .hasNotPassed("guess_higher")
                .hasNotPassed("guess_lower")
                .isEnded();
    }

    @Test
    @Deployment(resources = "process.bpmn")
    public void testNumberGuessTooHigh() {

    }

    @Test
    @Deployment(resources = "process.bpmn")
    public void testNumberGuessTooLow() {

    }

}
