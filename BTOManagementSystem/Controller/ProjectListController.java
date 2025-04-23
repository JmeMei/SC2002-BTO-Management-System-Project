package BTOManagementSystem.Controller;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Model.DAO.ProjectListDAO;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.View.*;

import java.util.ArrayList;

public class ProjectListController {

    ProjectListDAO dao = new ProjectListDAO();

    public void CreateNewProject(HDBManagerView managerView, HDBManagerCreateProjectView createProjectView) {


        if (dao.ManagerHasActiveProject(App.userSession.getName()) == 0){
            ArrayList<String> Data = createProjectView.Prompt();
            dao.CreateNewProject(Data);

        }else{
            createProjectView.AlreadyManagingProjectMessage();
        }


        managerView.showMenu();

    }

    public void EditProject(HDBManagerView managerView, HDBManagerEditProjectView editProjectView){



        Project p = dao.getProjectFromStringName(editProjectView.PromptProjectName());

        if (p != null){

            int atttribute = editProjectView.PromptAttribute();

            String newValue = editProjectView.PromptNewValue();

            dao.editProjectDetails(p, atttribute, newValue);

            editProjectView.ProjectUpdateSuccessMessage();

            managerView.showMenu();

        }else{

            editProjectView.ProjectDoesNotExistErrorMessage();
        }



    }

    public void DeleteProject(HDBManagerView managerView, HDBManagerDeleteProjectView deleteProjectView) {



        String ProjectName = deleteProjectView.promptProjectNameToDelete();
        boolean success = dao.DeleteProject(ProjectName);

        if(success == false){

            deleteProjectView.FailedMessage();

        }else{
            deleteProjectView.SucesssMessage();
        }

        managerView.showMenu();
    }

    public void ViewProjects(HDBManagerView managerView, HDBManagerViewProjectsView viewProjectsView){

        int filter = viewProjectsView.prompt();
        ArrayList<Project> projectArrayList = dao.LoadProjects(filter);

        int neighbourhood_filter = viewProjectsView.prompt_wantneightbourhoodfilter();

        if (neighbourhood_filter == 1){
            String Neightbourhood = viewProjectsView.PromptNeightbourhoodfilterValue();
            projectArrayList = dao.filterByNeightbourhood(projectArrayList, Neightbourhood);
        }
        viewProjectsView.DisplayProjects(dao.getHeaders(), projectArrayList);

        managerView.showMenu();

    }








}
