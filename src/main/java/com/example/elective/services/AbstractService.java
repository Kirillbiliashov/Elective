package com.example.elective.services;

import com.example.elective.dao.DAOFactory;


/**
 * Abstract base class for all service classes that provides utility methods for them
 * @author Kirill Biliashov
 */

public abstract class AbstractService {

  protected DAOFactory daoFactory = DAOFactory.getFactory(DAOFactory.MYSQL);

}
