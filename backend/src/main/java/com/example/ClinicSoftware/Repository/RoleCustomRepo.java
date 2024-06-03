package com.example.ClinicSoftware.Repository;

import java.util.List;

import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.example.ClinicSoftware.Entity.Role;
import com.example.ClinicSoftware.Entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository

public class RoleCustomRepo 
{
    @PersistenceContext
    private EntityManager entityManager;

    public List<Role> getRole(User user)
    {
        StringBuilder sql = new StringBuilder()
                .append("select r.name as name from users u join users_role ur on u.id = ur.users_id join roles r on r.id = ur.roles_id ");
        sql.append("Where 1=1 ");
        if(user.getEmail()!=null)
        {
            sql.append(" and email = :email");
        }

        NativeQuery<Role> query = ((Session) entityManager.getDelegate()) .createNativeQuery(sql.toString());
        System.out.println(query.getQueryString());
        if(user.getEmail()!=null)
        {
            query.setParameter("email", user.getEmail());
        }

        query.addScalar("name", StandardBasicTypes.STRING);
        query.setResultTransformer(Transformers.aliasToBean(Role.class));
        return query.list();
    }
}
