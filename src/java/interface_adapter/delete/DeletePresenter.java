package interface_adapter.delete;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.delete.DeleteOutputBoundary;
import use_case.delete.DeleteOutputData;

public class DeletePresenter implements DeleteOutputBoundary {
    private final SignupViewModel signUpViewModel;

    public DeletePresenter(SignupViewModel signUpViewModel){
        this.signUpViewModel = signUpViewModel;
    }
    @Override
    public void prepareSuccessViews(DeleteOutputData data){
        signUpViewModel.firePropertyChanged();
    }

}
