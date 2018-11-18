package rocket;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import rocket.utils.CommonParams;
import rocket.utils.SmartUnitTestService;
import rocket.utils.TestDataManager;
import rocket.utils.model.ProjectItem;
import rocket.utils.model.RegisterItem;
import rocket.utils.model.TaskItem;

import java.util.ArrayList;
import java.util.List;

public class SmartUnitTest {

    SmartUnitTestService testService;
    TestDataManager testData;

    @BeforeTest
    public void initTest(){
        System.out.println("=============== Test Start ===============");
        testService = new SmartUnitTestService();
        testService.start();

        testData = new TestDataManager();
        testData.prepareProjectTest(CommonParams.TEST_EXCEL_FILE);
        testData.prepareRegisterTest(CommonParams.TEST_EXCEL_FILE);
        testData.prepareTaskTest(CommonParams.TEST_EXCEL_FILE);
    }

    @AfterTest
    public void finishTest(){
        testService.stop();
        testService = null;
        testData = null;
        System.out.println("=============== End Test ===============");
    }

    @Test
    public void testRegister(){
        System.out.println("--------------- Register start ----------------");

        ArrayList<RegisterItem> list = testData.getRegisterList();

        for (int i = 0; i < list.size(); i++){
            RegisterItem item = list.get(i);

            int testResult = testService.testLicenseRegister(item.registerId);

            //TODO check result
            if (testResult == item.regResult){
                //
            }

            License lic = testService.testLicenseQuery();

            //TODO check license

        }

        System.out.println("--------------- Register end ----------------");
    }

    @Test
    public void testProject(){
        System.out.println("--------------- Register start ----------------");

        ArrayList<ProjectItem> list = testData.getProjectList();

        for (int i = 0; i < list.size(); i++){
            ProjectItem item = list.get(i);

            Task parseResult = testService.testParse(item.projectId, item.versionId, item.branchId, item.testerId, item.files, item.headers, item.macros);
            //TODO check

            Task genResult = testService.testTestCaseGen(item.projectId, item.versionId, item.branchId, item.testerId, item.files, item.headers, item.macros);
            //TODO check

            Task runResult = testService.testTestCaseRun(item.projectId, item.versionId, item.branchId, item.testerId, item.testCases, item.headers, item.macros);
            //TODO check

        }

        System.out.println("--------------- Register end ----------------");
    }

    @Test
    public void testTask(){
        System.out.println("--------------- Register start ----------------");

        ArrayList<TaskItem> list = testData.getTaskList();

        for (int i = 0; i < list.size(); i++){
            TaskItem item = list.get(i);

            List<Task> queryResult = testService.testTaskQuery(item.taskId);
            //TODO check

            Task pauseResult = testService.testTaskPause(item.taskId);
            //TODO check

            Task resumeResult = testService.testTaskResume(item.taskId);
            //TODO check

            Task cancelResult = testService.testTaskCancel(item.taskId);
            //TODO check
        }


        System.out.println("--------------- Register end ----------------");
    }

}
