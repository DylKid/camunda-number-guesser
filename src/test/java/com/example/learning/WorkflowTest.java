package com.example.learning;

import com.example.learning.model.CamundaVariableNames;
import com.example.learning.model.CamundaVariableNames.*;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.impl.VariableMapImpl;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.camunda.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import org.camunda.bpm.extension.mockito.DelegateExpressions.*;
import org.camunda.bpm.extension.mockito.*;

import java.util.Map;

@Deployment(resources = "process.bpmn")
public class WorkflowTest {

  @Rule
  public ProcessEngineRule processEngineRule = new StandaloneInMemoryTestConfiguration().rule();


  private RuntimeService runtimeService;

  @Before
  public void setup() {
    runtimeService = processEngineRule.getRuntimeService();
  }

  @Test
  public void stepThroughWhileCheating() {
    DelegateExpressions.registerJavaDelegateMock("FinishDelegate");
    final JavaDelegate finishDelegateMock = DelegateExpressions.getJavaDelegateMock("FinishDelegate");
    // get 'random' number of 2
    DelegateExpressions.registerJavaDelegateMock("GenerateRandomNumber")
            .onExecutionSetVariables(Map.of(
                    CamundaVariableNames.RANDOM_NUMBER, 2
            ));
    final JavaDelegate generateRandomNumberMock = DelegateExpressions.getJavaDelegateMock("GenerateRandomNumber");
    // guess two
    DelegateExpressions.registerJavaDelegateMock("MakeGuessDelegate")
            .onExecutionSetVariables(Map.of(
                    CamundaVariableNames.CURRENT_GUESS, 2,
                    CamundaVariableNames.GUESS_COUNT, 1
            ));
    final JavaDelegate makeGuessDelegateMock = DelegateExpressions.getJavaDelegateMock("MakeGuessDelegate");
    // guess lower & guess higher should /not/ be run in this case, but we'll set up mocks to break the test
    // if they do run
    DelegateExpressions.registerJavaDelegateMock("GuessHigherDelegate")
            .onExecutionThrowException(new Exception("GuessHigherDelegate should not run in this test case"));
    final JavaDelegate guessLowerDelegateMock = DelegateExpressions.getJavaDelegateMock("GuessHigherDelegate");
    DelegateExpressions.registerJavaDelegateMock("GuessLowerDelegate")
            .onExecutionThrowException(new Exception("GuessHigherDelegate should not run in this test case"));
    final JavaDelegate guessHigherDelegateMock = DelegateExpressions.getJavaDelegateMock("GuessLowerDelegate");

    // this doesn't work --- how do I get the "process" to run while using the delegates?
    DelegateExpressions.verifyJavaDelegateMock("GenerateRandomNumber").executed(times(1));
    DelegateExpressions.verifyJavaDelegateMock("MakeGuessDelegate").executed(times(1));
    DelegateExpressions.verifyJavaDelegateMock("GuessHigherDelegate").executedNever();
    DelegateExpressions.verifyJavaDelegateMock("GuessLowerDelegate").executedNever();

    Mocks.reset();
  }
}
