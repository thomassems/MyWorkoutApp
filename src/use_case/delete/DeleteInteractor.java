package use_case.delete;

public class DeleteInteractor implements DeleteInputBoundary{
    final DeleteUserDataAccessInterface userDataAccessObject;
    final DeleteOutputBoundary deletePresenter;
    public DeleteInteractor(DeleteUserDataAccessInterface userDataAccessInterface, DeleteOutputBoundary deleteOutputBoundary){
        this.userDataAccessObject = userDataAccessInterface;
        this.deletePresenter = deleteOutputBoundary;
    }
    @Override
    public void execute(DeleteInputData deleteInputData){
        userDataAccessObject.delete(deleteInputData.getUsername());
        DeleteOutputData deleteOutputData = new DeleteOutputData();
        deletePresenter.prepareSuccessViews(deleteOutputData);
    }
}
