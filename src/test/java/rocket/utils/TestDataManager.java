package rocket.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import rocket.License;
import rocket.Macro;
import rocket.Task;
import rocket.Testcase;
import rocket.utils.model.ProjectItem;
import rocket.utils.model.RegisterItem;
import rocket.utils.model.TaskItem;

import java.util.ArrayList;
import java.util.List;

public class TestDataManager {

    private ArrayList<RegisterItem> registerList;
    private ArrayList<ProjectItem> projectList;
    private ArrayList<TaskItem> taskList;

    public ArrayList<RegisterItem> getRegisterList() {
        return registerList;
    }

    public ArrayList<ProjectItem> getProjectList() {
        return projectList;
    }

    public ArrayList<TaskItem> getTaskList() {
        return taskList;
    }

    private RegisterItem parseToRegisterItem(JSONObject obj){
        RegisterItem item = null;

        if (obj == null) return null;

        item = new RegisterItem();

        item.registerId = obj.getString(CommonParams.REGISTER_ID_KEY);
        item.regResult = obj.getIntValue(CommonParams.REGISTER_RESULT_KEY);
        JSONObject licObj = obj.getJSONObject(CommonParams.REGISTER_LICENSE_KEY);

        if (licObj != null){
            License license = new License();
            license.code = licObj.getString(CommonParams.LICENSE_CODE_KEY);
            license.company = licObj.getString(CommonParams.LICENSE_COMPANY_KEY);
            license.expired_date = licObj.getString(CommonParams.LICENSE_EXPIRED_DATE_KEY);
            license.n_days = licObj.getIntValue(CommonParams.LICENSE_NDAYS_KEY);
            license.n_remainder_days = licObj.getIntValue(CommonParams.LICENSE_NREMAINDERDAYS_KEY);
            license.n_cpus = licObj.getIntValue(CommonParams.LICENSE_NCPUS_KEY);
            license.n_users = licObj.getIntValue(CommonParams.LICENSE_NUSERS_KEY);
            license.n_online_users = licObj.getIntValue(CommonParams.LICENSE_NONLINEUSERS_KEY);
            license.n_cpus_of_platform = licObj.getIntValue(CommonParams.LICENSE_NCPUSPLATFORM_KEY);
            license.n_tasks = licObj.getIntValue(CommonParams.LICENSE_NTASKS_KEY);
            license.n_users_of_tasks = licObj.getIntValue(CommonParams.LICENSE_NUSERSTASKS_KEY);

            item.expectLicense = license;
        }

        return item;
    }

    public void prepareRegisterTest(String filePath){
        List<List<String>> excelDatas = ExcelsHelper.readExcel(filePath);
        try {

            Assert.assertNotNull(excelDatas);

            registerList = new ArrayList<>();

            for (int i = 0; i < excelDatas.size(); i++){
                List<String> row = excelDatas.get(i);
                if (i >= CommonParams.REGISTER_TEST_DATA_Y){
                    String colTestParams = row.get(CommonParams.REGISTER_TEST_DATA_X);
                    JSONObject jo = JSON.parseObject(colTestParams);

                    RegisterItem item = parseToRegisterItem(jo);
                    if (item != null)
                        registerList.add(item);
                }
            }

        } catch (Exception e){

        }
    }

    private ProjectItem parseToProjectItem(JSONObject obj){
        ProjectItem item = null;
        if (obj == null) return null;

        item = new ProjectItem();

        item.projectId = obj.getString(CommonParams.PROJECT_ID_KEY);
        item.branchId = obj.getString(CommonParams.PROJECT_BRANCHID_KEY);
        item.versionId = obj.getString(CommonParams.PROJECT_VERSIONID_KEY);
        item.testerId = obj.getString(CommonParams.PROJECT_TESTERID_KEY);

        JSONArray fileArray = obj.getJSONArray(CommonParams.PROJECT_FILES_KEY);
        if (fileArray != null){
            ArrayList<String> fileList = new ArrayList<>();
            for (int i = 0; i < fileArray.size(); i++){
                String file = fileArray.getString(i);
                fileList.add(file);
            }
            item.files = fileList;
        }

        JSONArray headerArray = obj.getJSONArray(CommonParams.PROJECT_HEADERS_KEY);
        if (headerArray != null){
            ArrayList<String> headerList = new ArrayList<>();
            for (int i = 0; i < headerArray.size(); i++){
                String header = headerArray.getString(i);
                headerList.add(header);
            }
            item.headers = headerList;
        }

        JSONArray macroArray = obj.getJSONArray(CommonParams.PROJECT_MACROS_KEY);
        if (macroArray != null){
            ArrayList<Macro> macrosList = new ArrayList<>();
            for (int i = 0; i < macroArray.size(); i++){
                JSONObject mcObj = macroArray.getJSONObject(i);
                Macro mc = parseToMacro(mcObj);
                macrosList.add(mc);
            }
            item.macros = macrosList;
        }

        JSONArray testcaseArray = obj.getJSONArray(CommonParams.PROJECT_TESTCASES_KEY);
        if (testcaseArray != null){
            ArrayList<Testcase> testcaseList = new ArrayList<>();
            for (int i = 0; i < testcaseArray.size(); i++){
                JSONObject tcObj = testcaseArray.getJSONObject(i);
                Testcase tc = parseToTestCase(tcObj);
                testcaseList.add(tc);
            }
            item.testCases = testcaseList;
        }

        return item;
    }

    private Macro parseToMacro(JSONObject obj){
        Macro item = null;

        if (obj == null) return null;

        item = new Macro();

        item.name = obj.getString(CommonParams.MACRO_NAME_KEY);
        item.value = obj.getString(CommonParams.MACRO_VALUE_KEY);

        return item;
    }

    private Testcase parseToTestCase(JSONObject obj){
        Testcase tc = null;

        if (obj == null) return null;

        tc = new Testcase();

        tc.file = obj.getString(CommonParams.TESTCASE_FILE_KEY);
        tc.func = obj.getString(CommonParams.TESTCASE_FUNC_KEY);
        tc.kind = obj.getString(CommonParams.TESTCASE_KIND_KEY);
        tc.data = obj.getString(CommonParams.TESTCASE_DATA_KEY);

        tc.coverage = obj.getIntValue(CommonParams.TESTCASE_COVERAGE_KEY);

        return tc;
    }

    public void prepareProjectTest(String filePath) {
        List<List<String>> excelDatas = ExcelsHelper.readExcel(filePath);
        try {

            Assert.assertNotNull(excelDatas);

            projectList = new ArrayList<>();

            for (int i = 0; i < excelDatas.size(); i++){
                List<String> row = excelDatas.get(i);
                if (i >= CommonParams.PROJECT_TEST_DATA_Y){
                    String colTestParams = row.get(CommonParams.PROJECT_TEST_DATA_X);
                    JSONObject jo = JSON.parseObject(colTestParams);

                    ProjectItem item = parseToProjectItem(jo);
                    if (item != null)
                        projectList.add(item);
                }
            }

        } catch (Exception e){

        }
    }

    private Task parseToTask(JSONObject obj){
        Task t = null;

        if (obj == null) return null;

        t = new Task();

        t.id = obj.getString(CommonParams.TASK_ID_KEY);
        t.type = obj.getIntValue(CommonParams.TASK_TYPE_KEY);
        t.project_id = obj.getString(CommonParams.TASK_PROJECTID_KEY);
        t.version_id = obj.getString(CommonParams.TASK_VERSIONID_KEY);
        t.tester_id = obj.getString(CommonParams.TASK_TESTERID_KEY);
        t.count = obj.getIntValue(CommonParams.TASK_COUNT_KEY);
        t.create_time = obj.getString(CommonParams.TASK_CREATETIME_KEY);
        t.update_time = obj.getString(CommonParams.TASK_UPDATETIME_KEY);
        t.progress = obj.getIntValue(CommonParams.TASK_PROGRESS_KEY);
        t.status = obj.getIntValue(CommonParams.TASK_STATUS_KEY);

        return t;
    }

    private TaskItem parseToTaskItem(JSONObject obj){
        TaskItem item = null;

        if (obj == null) return null;

        item = new TaskItem();

        item.taskId = obj.getString(CommonParams.TASKITEM_ID_KEY);

        JSONObject tObj = obj.getJSONObject(CommonParams.TASKITEM_TASK_KEY);
        item.expectTask = parseToTask(tObj);

        return item;
    }

    public void prepareTaskTest(String filePath){
        List<List<String>> excelDatas = ExcelsHelper.readExcel(filePath);
        try {

            Assert.assertNotNull(excelDatas);

            taskList = new ArrayList<>();

            for (int i = 0; i < excelDatas.size(); i++){
                List<String> row = excelDatas.get(i);
                if (i >= CommonParams.TASK_TEST_DATA_Y){
                    String colTestParams = row.get(CommonParams.TASK_TEST_DATA_X);
                    JSONObject jo = JSON.parseObject(colTestParams);
                    TaskItem item = parseToTaskItem(jo);
                    if (item != null)
                        taskList.add(item);
                }
            }
        } catch (Exception e){

        }
    }

}
