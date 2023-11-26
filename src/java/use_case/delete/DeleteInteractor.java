package use_case.delete;

public class DeleteInteractor implements DeleteInputBoundary{
    final DeleteUserDataAccessInterface userDataAccessObject;
    final DeleteOutputBoundary deletePresenter;
    public DeleteInteractor(DeleteUserDataAccessInterface userDataAccessInterface, DeleteOutputBoundary deleteOutputBoundary){
        this.userDataAccessObject = userDataAccessInterface;
        this.deletePresenter = deleteOutputBoundary;
    }
    @Override
    /** Deletes a user from the database and then passes the output data to the view*/
    public void execute(DeleteInputData deleteInputData){
        userDataAccessObject.delete(deleteInputData.getUsername());
        DeleteOutputData deleteOutputData = new DeleteOutputData();
        deletePresenter.prepareSuccessViews(deleteOutputData);
    }
}
