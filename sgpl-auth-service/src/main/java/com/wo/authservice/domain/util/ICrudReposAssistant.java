package com.wo.authservice.domain.util;

import java.util.List;
import java.util.Optional;

public interface ICrudReposAssistant<DetailsResponse, ActionRequest, Id> {

    List<DetailsResponse> findAll();

    Optional<DetailsResponse> findById(Id id);

    void save(ActionRequest entity);

    void deleteById(Id id);

}
