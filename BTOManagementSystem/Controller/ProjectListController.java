package BTOManagementSystem.Controller;

import BTOManagementSystem.App.App;
import BTOManagementSystem.Model.DAO.ProjectListDAO;
import BTOManagementSystem.Model.Project;
import BTOManagementSystem.Model.Roles.HDBOfficer;
import BTOManagementSystem.View.*;

import java.util.ArrayList;
/**
 * Controller class responsible for handling operations related to BTO project management.
 * <p>
 * This includes creating, editing, deleting, and viewing project details,
 * primarily by HDB managers. It also supports fetching project details for officers.
 */
public class ProjectListController {

    ProjectListDAO dao = new ProjectListDAO();
    /**
     * Allows an HDB manager to create a new project if they are not already managing an active one.
     *
     * @param managerView the view used for general manager menu navigation
     * @param createProjectView the view used for prompting new project details
     */
    public void CreateNewProject(HDBManagerView managerView, HDBManagerCreateProjectView createProjectView) {


        if (dao.ManagerHasActiveProject(App.userSession.getName()) == 0){
            ArrayList<String> Data = createProjectView.Prompt();
            dao.CreateNewProject(Data);
            System.out.println("Project created successfully!");

        }else{
            createProjectView.AlreadyManagingProjectMessage();
        }

        managerView.showMenu();
    }

    /**
     * Allows an HDB manager to edit the details of an existing project.
     *
     * @param managerView the view used for general manager menu navigation
     * @param editProjectView the view used for prompting project name, attribute to edit, and new value
     */
    public void EditProject(HDBManagerView managerView, HDBManagerEditProjectView editProjectView){

        Project p = dao.getProjectFromStringName(editProjectView.PromptProjectName());

        if (p != null){

            int atttribute = editProjectView.PromptAttribute();

            String newValue = editProjectView.PromptNewValue();

            dao.editProjectDetails(p, atttribute, newValue);

            editProjectView.ProjectUpdateSuccessMessage();

        }else{

            editProjectView.ProjectDoesNotExistErrorMessage();
        }

        managerView.showMenu();

    }

    /**
     * Allows an HDB manager to delete an existing project by name.
     *
     * @param managerView the manager view to return to the main menu and for displaying messages
     * @param deleteProjectView the view used for prompting the project name to delete
     */
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
    /**
     * Allows an HDB manager to view a list of projects with optional neighborhood filtering.
     *
     * @param managerView the view used for general manager menu navigation
     * @param viewProjectsView the view used for displaying and filtering project listings
     */
    public void ViewProjects(HDBManagerView managerView, HDBManagerViewProjectsView viewProjectsView){

        int filter = viewProjectsView.prompt();

        ArrayList<Project> projectArrayList = dao.LoadProjects(filter);

        int neighbourhood_filter = viewProjectsView.prompt_wantneightbourhoodfilter();

        if (neighbourhood_filter == 1){
            String Neightbourhood = viewProjectsView.PromptNeightbourhoodfilterValue();
            projectArrayList = dao.filterByNeighbourhood(projectArrayList, Neightbourhood);
        }
        viewProjectsView.DisplayProjects(dao.getHeaders(), projectArrayList);

        managerView.showMenu();
    }

    /**
     * Retrieves the name of the project assigned to a specific HDB officer.
     *
     * @param officer the officer whose approved project name is being retrieved
     * @return the name of the project assigned to the officer, or {@code null} if none
     */
    public String getApprovedProjectName(HDBOfficer officer){

        String projectName = dao.getProjectNamefromOfficerName(officer.getName());
        return projectName;
    }

    /**
     * Retrieves the details of a project given its name.
     *
     * @param projectName the name of the project
     * @return the {@link Project} object containing project details, or {@code null} if not found
     */
    public Project getProjectDetails(String projectName){

        Project project = dao.getProjectFromStringName(projectName);
        return project;
    }
}
