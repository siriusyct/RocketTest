package rocket.utils.model;

import rocket.Macro;
import rocket.Task;
import rocket.Testcase;

import java.util.ArrayList;
import java.util.List;

public class ProjectItem {
    public String projectId;
    public String versionId;
    public String branchId;
    public String testerId;
    public List<String> files;
    public List<String> headers;
    public List<Macro> macros;
    public List<Testcase> testCases;

    public Task expectTask;

    public ProjectItem() {
        this.projectId = "";
        this.versionId = "";
        this.branchId = "";
        this.testerId = "";
        this.files = new ArrayList<>();
        this.headers = new ArrayList<>();
        this.macros = new ArrayList<>();
        this.testCases = new ArrayList<>();
    }
}
