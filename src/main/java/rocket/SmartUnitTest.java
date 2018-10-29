package rocket;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.ArrayList;
import java.util.List;

public class SmartUnitTest {

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

    public void runTest(){
        if (client == null || transport == null)
            return;

        testLicenseRegister();

        testLicenseQuery();

        testParse();

        testTestCaseGen();

        testTestCaseRun();

    }

    private void testLicenseRegister(){
        try {
            int result = client.license_register("test_id");
            System.out.println("license_register result : " + result);
        } catch (TException e){
            e.printStackTrace();
        }
    }

    private void testLicenseQuery(){
        try {
            License result = client.license_query();
            System.out.println("license_query result : " + result);
        } catch (TException e){
            e.printStackTrace();
        }
    }

    private void testParse(){
        try {
            List<String> files = new ArrayList();
            List<String> headers = new ArrayList();
            List<Macro> macros = new ArrayList();
            Task result = client.parse("aaa", "1.0", "xyz", "123", files, headers, macros);
            System.out.println("parse result : " + result);
        } catch (TException e){
            e.printStackTrace();
        }
    }

    private void testTestCaseGen(){
        try {
            List<String> files = new ArrayList();
            List<String> headers = new ArrayList();
            List<Macro> macros = new ArrayList();
            Task result = client.testcase_gen("aaa", "1.0", "xyz", "123", files, headers, macros);
            System.out.println("TestCaseGen result : " + result);
        } catch (TException e){
            e.printStackTrace();
        }
    }

    private void testTestCaseRun(){
        try {
            List<Testcase> testcases = new ArrayList();
            List<String> headers = new ArrayList();
            List<Macro> macros = new ArrayList();
            Task result = client.testcase_run("aaa", "1.0", "xyz", "123", testcases, headers, macros);
            System.out.println("TestCaseRun result : " + result);
        } catch (TException e){
            e.printStackTrace();
        }
    }

    private List<Task> testTaskQuery(){
        List<Task> result = null;
        try {
            result = client.task_query("aaa");
            System.out.println("TestCaseRun result : " + result);
        } catch (TException e){
            e.printStackTrace();
        }
        return result;
    }

    private void testTaskPause(){
        try {
            Task result = client.task_pause("aaa");
            System.out.println("TestCaseRun result : " + result);
        } catch (TException e){
            e.printStackTrace();
        }
    }

    private void testTaskResume(){
        try {
            Task result = client.task_resume("aaa");
            System.out.println("TestCaseRun result : " + result);
        } catch (TException e){
            e.printStackTrace();
        }
    }

    private void testTaskCancel(){
        try {
            Task result = client.task_cancel("aaa");
            System.out.println("TestCaseRun result : " + result);
        } catch (TException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SmartUnitTest client = new SmartUnitTest();
        client.start();
        client.runTest();
        client.stop();
    }

}
