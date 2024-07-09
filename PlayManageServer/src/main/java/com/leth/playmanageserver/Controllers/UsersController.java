package com.leth.playmanageserver.Controllers;

import com.leth.playmanageserver.Configs.ResultJson;
import com.leth.playmanageserver.Pojos.Users;
import com.leth.playmanageserver.Services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UsersController {
    @Autowired //注入service对象
    private UsersServices usersServices;

    @RequestMapping("insertUsers")
    public ResultJson insertUsers(Users users) {
        int result = usersServices.insertUsers(users);
        if(result == 1){
            return ResultJson.success();
        }else{
            return ResultJson.error();
        }
    }

    @RequestMapping("deleteUsers")
    public ResultJson deleteUsers(int id) {
        int result = usersServices.deleteUsers(id);
        if(result == 1){
            return ResultJson.success();
        }else{
            return ResultJson.error();
        }
    }

    @RequestMapping("updateUsers")
    public ResultJson updateUsers(Users users) {
        int result = usersServices.updateUsers(users);
        if(result == 1){
            return ResultJson.success();
        }else{
            return ResultJson.error();
        }
    }

    @RequestMapping("listUsersAll")
    public ResultJson listUsersAll() {
        List<Users> result = usersServices.listUsersAll();
        if(!result.isEmpty()){
            return ResultJson.success(result);
        }else{
            return ResultJson.error();
        }
    }

    @RequestMapping("loginUsers")
    public ResultJson loginUsers(Users users) {
        List<Users> result = usersServices.loginUsers(users);
        if(!result.isEmpty()){
            return ResultJson.success(result);
        }else{
            return ResultJson.error();
        }
    }

    @RequestMapping("searchUsers")
    public ResultJson searchUsers(Users users) {
        List<Users> result = usersServices.searchUsers(users);
        if(!result.isEmpty()){
            return ResultJson.success(result);
        }else{
            return ResultJson.error();
        }
    }

    @RequestMapping("searchUsersOne")
    public ResultJson searchUsersOne(int id) {
        List<Users> result = usersServices.searchUsersOne(id);
        if(!result.isEmpty()){
            return ResultJson.success(result);
        }else{
            return ResultJson.error();
        }
    }

    @RequestMapping("updateUsersPassword")
    public ResultJson updateUsersPassword(Users users) {
        int result = usersServices.updateUsersPassword(users);
        if(result == 1){
            return ResultJson.success();
        }else{
            return ResultJson.error();
        }
    }
}


/*这段代码是一个基于Spring框架的REST控制器，用于处理与用户相关的操作。让我们逐行分析并提出问题。

@RestController 和 @CrossOrigin(origins = "*") 注解指示了这是一个REST控制器，并启用了跨域资源共享（CORS）支持。你是如何决定在这个控制器中启用跨域支持的？

@Autowired 注解注入了 UsersServices 对象，以便在控制器中调用与用户相关的服务。你能解释一下为什么在控制器中需要注入服务对象吗？

@RequestMapping 注解定义了多个REST端点，包括插入、删除、更新、获取全部、登录、搜索和根据ID查询用户信息的端点。你能解释一下为什么选择这些特定的端点名称吗？

insertUsers 方法用于插入新用户，它接收一个 Users 对象作为参数，并返回一个表示操作结果的 ResultJson 对象。你能描述一下在插入新用户时可能遇到的异常情况吗？

deleteUsers 方法用于删除用户，它接收一个用户ID作为参数，并返回一个表示操作结果的 ResultJson 对象。在这个方法中，为什么选择使用GET请求而不是POST请求？

updateUsers 方法用于更新用户信息，它接收一个 Users 对象作为参数，并返回一个表示操作结果的 ResultJson 对象。在更新用户信息时，有哪些情况可能导致更新失败？

listUsersAll 方法用于获取所有用户信息，它返回一个包含用户信息的 ResultJson 对象。如果数据库中没有用户信息，该方法会返回什么结果？

loginUsers 方法用于用户登录，它接收一个 Users 对象作为参数，并返回一个包含用户信息的 ResultJson 对象。在用户登录时，如何验证用户的身份？

searchUsers 和 searchUsersOne 方法用于搜索用户信息，它们接收一个 Users 对象或一个用户ID作为参数，并返回符合条件的用户信息。你能解释一下在这两个方法中搜索用户信息的具体实现吗？

updateUsersPassword 方法用于更新用户密码，它接收一个 Users 对象作为参数，并返回一个表示操作结果的 ResultJson 对象。在更新用户密码时，有哪些因素需要考虑？*/


/*你是如何决定在这个控制器中启用跨域支持的？

回答：在开发Web应用时，通常会涉及到前后端分离的架构，前端和后端可能部署在不同的域上。为了让前端能够跨域访问后端的接口，需要启用跨域资源共享（CORS）支持。因此，在控制器类上使用 @CrossOrigin(origins = "*") 注解，允许来自任何来源的跨域请求访问该控制器的接口。
你能解释一下为什么在控制器中需要注入服务对象吗？

回答：在控制器中需要注入服务对象是为了调用与业务逻辑相关的服务方法。服务对象负责执行具体的业务逻辑，例如处理数据库操作、验证用户身份等。通过在控制器中注入服务对象，可以使控制器与具体的业务逻辑解耦，提高代码的可维护性和可测试性。
在这个方法中，为什么选择使用GET请求而不是POST请求？

回答：在RESTful API设计中，根据HTTP方法的语义来选择请求方法是很重要的。通常情况下，GET请求用于从服务器获取资源，而不对服务器进行任何修改。在这个方法中，删除用户的操作属于对服务器资源的修改，因此使用GET请求并不符合HTTP方法的语义，正确的做法是使用DELETE请求来删除资源。
在插入新用户时可能遇到的异常情况是什么？

回答：在插入新用户时可能会遇到多种异常情况，例如：
数据库连接失败。
用户名或其他字段的唯一约束冲突，导致无法插入重复的用户信息。
插入的用户信息不符合数据库的约束条件，例如字段长度限制、数据类型不匹配等。
在这些异常情况下，可能会抛出相应的异常或返回特定的错误码，应该在代码中进行捕获和处理，并向客户端返回合适的错误信息。
在更新用户信息时，有哪些情况可能导致更新失败？

回答：更新用户信息时可能会遇到多种情况导致更新失败，例如：
更新的用户ID不存在或不合法。
更新的用户信息与数据库中已有的信息冲突，例如唯一约束冲突。
更新的用户信息不符合数据库的约束条件，例如字段长度限制、数据类型不匹配等。
在这些情况下，更新操作可能会抛出异常或返回失败的状态码，应该在代码中进行处理并向客户端返回适当的错误信息。
在用户登录时，如何验证用户的身份？

回答：用户登录时通常需要验证用户名和密码的正确性。在登录接口中，可以接收用户提交的用户名和密码，并与数据库中存储的用户信息进行比对。如果用户名和密码匹配，则认为登录成功，返回相应的用户信息；如果不匹配，则返回登录失败的提示信息。另外，为了增强安全性，还可以考虑使用加密算法对用户密码进行加密存储，并在比对时进行解密。
你能解释一下在这两个方法中搜索用户信息的具体实现吗？

回答：在 searchUsers 和 searchUsersOne 方法中，搜索用户信息的具体实现取决于传入的参数。这两个方法接收一个 Users 对象或一个用户ID作为参数，并根据参数中指定的条件在数据库中查询符合条件的用户信息。搜索过程通常涉及到使用SQL查询语句或调用ORM框架提供的查询方法。如果查询到了符合条件的用户信息，则返回查询结果；如果未查询到结果，则返回空的列表或失败的状态码。
在更新用户密码时，有哪些因素需要

考虑？

回答：在更新用户密码时，需要考虑以下因素：
安全性：密码应该采用安全的加密算法进行存储，例如哈希算法，并且不应该以明文形式存储在数据库中。
合法性验证：更新的密码应该符合一定的复杂度要求，例如长度、包含大小写字母、数字和特殊字符等。
用户身份验证：在更新密码之前，应该验证用户的身份，确保只有合法的用户才能修改密码。
数据库事务：更新密码的操作可能涉及到数据库的事务处理，确保密码的更新是原子性的，不会出现部分更新成功的情况。
错误处理：如果更新密码的操作失败，应该向客户端返回相应的错误信息，以便用户了解失败的原因，并采取相应的措施。
通过考虑这些因素，可以确保更新用户密码的操作安全可靠，并且能够有效地保护用户的信息安全。*/