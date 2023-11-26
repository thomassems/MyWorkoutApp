package interface_adapter.delete;

import use_case.delete.DeleteInputBoundary;
import use_case.delete.DeleteInputData;

public class DeleteController{
    final DeleteInputBoundary deleteUseCaseInteractor;
    public DeleteController(DeleteInputBoundary deleteUseCaseInteractor){
        this.deleteUseCaseInteractor = deleteUseCaseInteractor;
    }
    public void execute(String username){
        DeleteInputData deleteInputData = new DeleteInputData(username);
        deleteUseCaseInteractor.execute(deleteInputData);
    }
}
