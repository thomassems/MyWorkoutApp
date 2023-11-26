package interface_adapter.delete;

import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInputData;

public class DeleteController{
    final DeleteInputBoundary deleteUseCaseInteractor;
    public DeleteController(DeleteInputBoundary deleteUseCaseInteractor){
        this.deleteUseCaseInteractor = deleteUseCaseInteractor;
    }
    /** Executes the controller by passing by creating input data with the username and passing it to the interactor */
    public void execute(String username){
        DeleteInputData deleteInputData = new DeleteInputData(username);
        deleteUseCaseInteractor.execute(deleteInputData);
    }
}
