/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.provide.UserModel;

/**
 *
 * @author Truong Van Dung
 */
public class ChainSearchUser {

    public AbstractSearchByAll chain() {
        AbstractSearchByAll id = new SelectUserById();
        AbstractSearchByAll date = new SelectUserByDate();
        AbstractSearchByAll user = new SelectUserByUsername();
        AbstractSearchByAll sex = new SelectUserBySex();

        id.setNextSearch(user);
        user.setNextSearch(sex);
        sex.setNextSearch(date);

        return id;
    }
}
