package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import org.acme.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, UUID> {

    public List<User> findAll(Sort sort, Page page) {
        return findAll(sort).page(page).list();
    }

    public Optional<User> findByLogin(String login) {
        User user = find("login", login).firstResult();
        return Optional.ofNullable(user);
    }
}
