package interface_adapter.delete;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.delete.DeleteOutputBoundary;
import use_case.delete.DeleteOutputData;

public class DeletePresenter implements DeleteOutputBoundary {
    private final SignupViewModel signUpViewModel;
    private final ViewManagerModel viewManagerModel;

    /** Initializes the delete presenter */
    public DeletePresenter(SignupViewModel signUpViewModel, ViewManagerModel viewManagerModel){
        this.signUpViewModel = signUpViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    /** Notifies the view model of a property change and updates the view to the signup view */
    public void prepareSuccessViews(DeleteOutputData data){
        viewManagerModel.setActiveView(signUpViewModel.getViewName());
        signUpViewModel.firePropertyChanged();
    }

}
