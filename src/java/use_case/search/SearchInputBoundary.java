package use_case.search;

import java.io.IOException;

public interface SearchInputBoundary {
    void execute(SearchInputData searchInputData) throws IOException;
}
