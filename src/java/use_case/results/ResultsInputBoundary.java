package use_case.results;

import use_case.search.SearchInputData;

import java.io.IOException;

public interface ResultsInputBoundary {
    void execute(ResultsInputData searchInputData) throws IOException;
}
