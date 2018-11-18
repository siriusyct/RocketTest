package rocket.utils;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
<<<<<<< HEAD:src/test/java/rocket/utils/SmartUnitTestService.java
import rocket.*;
=======
import rocket.utils.ExcelsHelper;
>>>>>>> b35936bf664750c38daf28dd2c8836ee15f146c6:src/main/java/rocket/SmartUnitTest.java

import java.util.ArrayList;
import java.util.List;

public class SmartUnitTestService {

    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8090;
    private static final int TIMEOUT = 30000;

    SmartUnit.Client client = null;
    TTransport transport = null;

    public void start(){
        try {
            transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            // TProtocol protocol = new TCompactProtocol(transport);
            // TProtocol protocol = new TJSONProtocol(transport);
            SmartUnit.Client client = new SmartUnit.Client(protocol);
            transport.open();

            System.out.println("Client start.");
        } catch (TException e) {
            System.out.println("Client start exception.");
            e.printStackTrace();
        }
    }

    public void stop(){
        if (null != transport) {
            transport.close();
            System.out.println("Client stop.");
        }
    }

<<<<<<< HEAD:src/test/java/rocket/utils/SmartUnitTestService.java
    public int testLicenseRegister(String regId){
        int result = 0;
        try {
            result = client.license_register(regId);
=======
    public void runTest(){
        if (client == null || transport == null)
            return;

        List<List<String>> regIds = ExcelsHelper.readExcel("D:\\GitHub\\Pipi\\RocketTest\\testcase.xlsx");
        if (regIds != null){
            for (int i = 0; i < regIds.size(); i++){
                String regId = regIds.get(i).get(0);
                System.out.println("regId  : " + regId);
                testLicenseRegister(regId);
            }
        }

//        testLicenseQuery();
//
//        testParse();
//
//        testTestCaseGen();
//
//        testTestCaseRun();

    }

    private void testLicenseRegister(String registerId){
        try {
            int result = client.license_register(registerId);
>>>>>>> b35936bf664750c38daf28dd2c8836ee15f146c6:src/main/java/rocket/SmartUnitTest.java
            System.out.println("license_register result : " + result);
        } catch (TException e){
            e.printStackTrace();
        }
        return result;
    }

    public License testLicenseQuery(){
        License result = null;
        try {
            result = client.license_query();
            System.out.println("license_query result : " + result);
        } catch (TException e){
            e.printStackTrace();
        }
        return result;
    }

<<<<<<< HEAD:src/test/java/rocket/utils/SmartUnitTestService.java
    public Task testParse(String projecId, String versionId, String branchId, String testerId,
                           java.util.List<String> files, java.util.List<String> headers, java.util.List<Macro> macros){
        Task result = null;
        try {
            result = client.parse(projecId, versionId, branchId, testerId, files, headers, macros);
=======
    private void testParse(String projectId, String versionId, String branchId, String testerId,
                           List<String> files, List<String> headers, List<Macro> macros){
        try {
            Task result = client.parse(projectId, versionId, branchId, testerId, files, headers, macros);
>>>>>>> b35936bf664750c38daf28dd2c8836ee15f146c6:src/main/java/rocket/SmartUnitTest.java
            System.out.println("parse result : " + result);
        } catch (TException e){
            e.printStackTrace();
        }

        return result;
    }

<<<<<<< HEAD:src/test/java/rocket/utils/SmartUnitTestService.java
    public Task testTestCaseGen(String projecId, String versionId, String branchId, String testerId,
                                 java.util.List<String> files, java.util.List<String> headers, java.util.List<Macro> macros){
        Task result = null;
        try {
            result = client.testcase_gen(projecId, versionId, branchId, testerId, files, headers, macros);
=======
    private void testTestCaseGen(String projectId, String versionId, String branchId, String testerId,
                                 List<String> files, List<String> headers, List<Macro> macros){
        try {
            Task result = client.testcase_gen(projectId, versionId, branchId, testerId, files, headers, macros);
>>>>>>> b35936bf664750c38daf28dd2c8836ee15f146c6:src/main/java/rocket/SmartUnitTest.java
            System.out.println("TestCaseGen result : " + result);
        } catch (TException e){
            e.printStackTrace();
        }
        return result;
    }

<<<<<<< HEAD:src/test/java/rocket/utils/SmartUnitTestService.java
    public Task testTestCaseRun(String projecId, String versionId, String branchId, String testerId,
                                 java.util.List<Testcase> testCases, java.util.List<String> headers, java.util.List<Macro> macros){
        Task result = null;
        try {
            result = client.testcase_run(projecId, versionId, branchId, testerId, testCases, headers, macros);
=======
    private void testTestCaseRun(String projectId, String versionId, String branchId, String testerId,
                                 List<Testcase> testcases, List<String> headers, List<Macro> macros){
        try {
            Task result = client.testcase_run(projectId, versionId, branchId, testerId, testcases, headers, macros);
>>>>>>> b35936bf664750c38daf28dd2c8836ee15f146c6:src/main/java/rocket/SmartUnitTest.java
            System.out.println("TestCaseRun result : " + result);
        } catch (TException e){
            e.printStackTrace();
        }
        return result;
    }

<<<<<<< HEAD:src/test/java/rocket/utils/SmartUnitTestService.java
    public List<Task> testTaskQuery(String taskId){
        List<Task> result = null;
        try {
            result = client.task_query(taskId);
            System.out.println("TestCaseRun result : " + result);
=======
    private List<Task> testTaskQuery(String taskId){
        List<Task> result = null;
        try {
            result = client.task_query(taskId);
            System.out.println("TaskQuery result : " + result);
>>>>>>> b35936bf664750c38daf28dd2c8836ee15f146c6:src/main/java/rocket/SmartUnitTest.java
        } catch (TException e){
            e.printStackTrace();
        }
        return result;
    }

<<<<<<< HEAD:src/test/java/rocket/utils/SmartUnitTestService.java
    public Task testTaskPause(String taskId){
        Task result = null;
        try {
            result = client.task_pause(taskId);
            System.out.println("TestCaseRun result : " + result);
=======
    private void testTaskPause(String taskId){
        try {
            Task result = client.task_pause(taskId);
            System.out.println("testTaskPause result : " + result);
>>>>>>> b35936bf664750c38daf28dd2c8836ee15f146c6:src/main/java/rocket/SmartUnitTest.java
        } catch (TException e){
            e.printStackTrace();
        }
        return result;
    }

<<<<<<< HEAD:src/test/java/rocket/utils/SmartUnitTestService.java
    public Task testTaskResume(String taskId){
        Task result = null;
        try {
            result = client.task_resume(taskId);
            System.out.println("TestCaseRun result : " + result);
=======
    private void testTaskResume(String taskId){
        try {
            Task result = client.task_resume(taskId);
            System.out.println("TaskResume result : " + result);
>>>>>>> b35936bf664750c38daf28dd2c8836ee15f146c6:src/main/java/rocket/SmartUnitTest.java
        } catch (TException e){
            e.printStackTrace();
        }
        return result;
    }

<<<<<<< HEAD:src/test/java/rocket/utils/SmartUnitTestService.java
    public Task testTaskCancel(String taskId){
        Task result = null;
        try {
            result = client.task_cancel(taskId);
            System.out.println("TestCaseRun result : " + result);
=======
    private void testTaskCancel(String taskId){
        try {
            Task result = client.task_cancel("aaa");
            System.out.println("TaskCancel result : " + result);
>>>>>>> b35936bf664750c38daf28dd2c8836ee15f146c6:src/main/java/rocket/SmartUnitTest.java
        } catch (TException e){
            e.printStackTrace();
        }
        return result;
    }

//    public static void main(String[] args) {
//        SmartUnitTestService client = new SmartUnitTestService();
//        client.start();
//        client.runTest();
//        client.stop();
//    }

}
