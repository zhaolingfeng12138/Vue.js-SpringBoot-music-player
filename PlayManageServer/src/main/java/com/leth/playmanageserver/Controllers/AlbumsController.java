package com.leth.playmanageserver.Controllers;

import com.leth.playmanageserver.Configs.ResultJson;
import com.leth.playmanageserver.Pojos.Albums;
import com.leth.playmanageserver.Services.AlbumsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/*
这些语句导入了代码中需要使用的类和包。其中：
ResultJson 是一个用于封装返回结果的类；
Albums 是一个专辑对象的POJO类；
AlbumsServices 是处理专辑相关业务逻辑的服务类；
@Autowired 注解用于进行依赖注入；
@CrossOrigin 注解允许跨域请求；
@RequestMapping 和 @RestController 注解用于定义RESTful风格的控制器。
*/

@RestController
@CrossOrigin(origins = "*")
public class AlbumsController {
    @Autowired //注入service对象
    private AlbumsServices albumsServices;
    /*
    这段代码定义了一个名为 AlbumsController 的控制器类，并使用 @RestController 注解表示这是一个RESTful风格的控制器。
    @CrossOrigin(origins = "*") 注解表示允许跨域请求。接着通过 @Autowired 注解注入了一个 AlbumsServices 类的实例。
在这段代码中，我们定义了一个控制器类，并且注入了一个服务类的实例，准备在该控制器中处理与专辑相关的业务逻辑。
*/

    @RequestMapping("insertAlbums")
    /*这段代码定义了一个用于处理插入专辑信息的请求的方法，通过 @RequestMapping("insertAlbums") 注解将该方法映射到路径 "/insertAlbums" 上。*/
    public ResultJson insertAlbums(Albums albums) {
        int result = albumsServices.insertAlbums(albums);
        if(result == 1){
            return ResultJson.success();
        }else{
            return ResultJson.error();
        }
    }
/*
这个方法接受一个 Albums 对象作为参数，表示要插入的专辑信息。在方法体内，首先调用了 albumsServices.insertAlbums(albums) 方法来执行实际的插入操作，返回一个表示插入结果的整数值。
如果插入成功（即 result == 1），则返回一个表示成功的 ResultJson 对象，通过调用 ResultJson.success() 方法创建。否则，返回一个表示失败的 ResultJson 对象，通过调用 ResultJson.error() 方法创建。
这段代码的作用是将传入的专辑信息插入到数据库中，并根据插入结果返回相应的成功或失败信息给调用者。
*/
    @RequestMapping("deleteAlbums")
    /*这段代码定义了一个用于处理删除专辑信息的请求的方法，通过 @RequestMapping("deleteAlbums") 注解将该方法映射到路径 "/deleteAlbums" 上。*/
    public ResultJson deleteAlbums(int id) {
        int result = albumsServices.deleteAlbums(id);
        if(result == 1){
            return ResultJson.success();
        }else{
            return ResultJson.error();
        }
    }
/*
这个方法接受一个整数类型的参数 id，表示要删除的专辑的标识符。在方法体内，首先调用了 albumsServices.deleteAlbums(id) 方法来执行实际的删除操作，返回一个表示删除结果的整数值。
如果删除成功（即 result == 1），则返回一个表示成功的 ResultJson 对象，通过调用 ResultJson.success() 方法创建。否则，返回一个表示失败的 ResultJson 对象，通过调用 ResultJson.error() 方法创建。
这段代码的作用是根据传入的专辑ID从数据库中删除对应的专辑信息，并根据删除结果返回相应的成功或失败信息给调用者。
*/
    @RequestMapping("updateAlbums")
    /*这段代码定义了一个用于处理更新专辑信息的请求的方法，通过 @RequestMapping("updateAlbums") 注解将该方法映射到路径 "/updateAlbums" 上。*/
    public ResultJson updateAlbums(Albums albums) {
        int result = albumsServices.updateAlbums(albums);
        if(result == 1){
            return ResultJson.success();
        }else{
            return ResultJson.error();
        }
    }
/*这个方法接受一个 Albums 对象作为参数，表示要更新的专辑信息。在方法体内，首先调用了 albumsServices.updateAlbums(albums) 方法来执行实际的更新操作，返回一个表示更新结果的整数值。
如果更新成功（即 result == 1），则返回一个表示成功的 ResultJson 对象，通过调用 ResultJson.success() 方法创建。否则，返回一个表示失败的 ResultJson 对象，通过调用 ResultJson.error() 方法创建。
这段代码的作用是根据传入的专辑信息更新数据库中对应的专辑信息，并根据更新结果返回相应的成功或失败信息给调用者。*/
    @RequestMapping("listAlbumsAll")
    /*这段代码定义了一个用于获取所有专辑信息的方法，通过 @RequestMapping("listAlbumsAll") 注解将该方法映射到路径 "/listAlbumsAll" 上。*/
    public ResultJson listAlbumsAll() {
        List<Albums> result = albumsServices.listAlbumsAll();
        if(!result.isEmpty()){
            return ResultJson.success(result);
        }else{
            return ResultJson.error();
        }
    }
/*该方法不接受任何参数。在方法体内，首先调用了 albumsServices.listAlbumsAll() 方法来获取所有专辑的列表。然后，通过判断结果列表是否为空，如果不为空，则返回一个表示成功并携带专辑列表的 ResultJson 对象，通过调用 ResultJson.success(result) 方法创建。否则，返回一个表示失败的 ResultJson 对象，通过调用 ResultJson.error() 方法创建。
这段代码的作用是从数据库中获取所有专辑信息，并根据获取结果返回相应的成功或失败信息给调用者。*/
    @RequestMapping("searchAlbums")
    public ResultJson searchAlbums(Albums albums) {
        List<Albums> result = albumsServices.searchAlbums(albums);
        if(!result.isEmpty()){
            return ResultJson.success(result);
        }else{
            return ResultJson.error();
        }
    }

    @RequestMapping("searchAlbumsOne")
    /*这段代码定义了一个用于搜索符合条件的专辑信息的方法，通过 @RequestMapping("searchAlbums") 注解将该方法映射到路径 "/searchAlbums" 上。*/
    public ResultJson searchAlbumsOne(int id) {
        List<Albums> result = albumsServices.searchAlbumsOne(id);
        if(!result.isEmpty()){
            return ResultJson.success(result);
        }else{
            return ResultJson.error();
        }
    }
}
/*这个方法接受一个 Albums 对象作为参数，表示搜索请求中提供的专辑信息条件。在方法体内，首先调用了 albumsServices.searchAlbums(albums) 方法来执行实际的搜索操作，返回一个表示搜索结果的专辑列表。

然后，通过判断结果列表是否为空，如果不为空，则表示搜索到了符合条件的专辑，返回一个表示成功并携带专辑列表的 ResultJson 对象，通过调用 ResultJson.success(result) 方法创建。否则，返回一个表示搜索失败的 ResultJson 对象，通过调用 ResultJson.error() 方法创建。

这段代码的作用是根据传入的专辑信息条件进行搜索，并根据搜索结果返回相应的成功或失败信息给调用者。*/


/*
根据提供的代码片段，我们可以看到这是一个用于管理专辑信息的控制器类。让我们来分析一下其功能实现和数据交互。
功能实现：
添加专辑 (insertAlbums)： 允许用户通过提供专辑信息来添加新的专辑。
删除专辑 (deleteAlbums)： 允许用户通过提供专辑ID来删除指定的专辑。
更新专辑信息 (updateAlbums)： 允许用户通过提供更新后的专辑信息来更新已有的专辑信息。
获取所有专辑列表 (listAlbumsAll)： 允许用户获取系统中所有专辑的列表。
搜索专辑 (searchAlbums)： 允许用户根据给定的条件搜索符合条件的专辑信息。
搜索单个专辑 (searchAlbumsOne)： 允许用户根据专辑ID搜索单个专辑的信息。
数据交互：
用户通过发送HTTP请求到对应的URL路径（如"/insertAlbums"、"/deleteAlbums"等），触发相应的功能操作。
控制器（AlbumsController）接收到请求后，根据请求的类型调用相应的方法来处理请求。
控制器方法内部调用服务层（AlbumsServices）的方法来执行具体的业务逻辑，如插入、删除、更新、查询等操作。
服务层方法与数据访问层进行交互，通过数据访问对象来访问数据库，并执行相应的SQL语句来操作数据库。
数据访问层将操作结果返回给服务层，服务层再将结果返回给控制器。
控制器根据操作结果，构造相应的JSON格式数据并返回给前端，以完成数据交互过程。
通过这样的功能实现和数据交互流程，专辑管理系统能够实现对专辑信息的增删改查等管理功能，并与前端进行数据交互。
*/



/*
当分析代码时，针对技术栈提出以下问题会更具针对性：

Spring Boot框架：

为什么选择了Spring Boot框架来开发后端服务？有哪些优势？
Spring Boot是如何简化项目配置和快速搭建后端服务的？
在Spring Boot中，如何定义RESTful风格的控制器？
Spring框架：

为什么使用了Spring的依赖注入和控制反转（IoC）特性？
在Spring中，如何实现依赖注入和控制反转？
如何在Spring中定义和管理服务类（Service）和控制器类（Controller）？
Spring MVC模块：

Spring MVC是如何支持RESTful风格的控制器？
如何使用Spring MVC来处理HTTP请求和响应？
在Spring MVC中，如何定义请求映射和处理方法？
数据库访问：

为什么选择了MySQL作为后端数据库？
在Spring中，如何实现与MySQL数据库的交互？
如何使用Spring的JdbcTemplate或Spring Data JPA等来简化数据库访问？
跨域请求处理：

为什么需要处理跨域请求？跨域请求是如何影响项目的？
在Spring Boot中，如何配置允许跨域请求？
如何通过Spring Boot的 @CrossOrigin 注解来处理跨域请求？

问题1：为什么选择了Spring Boot框架来开发后端服务？有哪些优势？

回答：我们选择了Spring Boot框架来开发后端服务，主要基于以下几个优势：

快速搭建： Spring Boot提供了快速搭建项目的能力，通过自动配置和约定优于配置的原则，可以快速地启动和部署项目，减少了开发人员的工作量。
简化配置： Spring Boot采用了约定大于配置的思想，减少了繁琐的配置，开发者可以专注于业务逻辑的实现，提高了开发效率。
集成度高： Spring Boot集成了大量的常用功能和第三方库，如安全性、数据库访问、RESTful支持等，开发者可以方便地引入这些功能，提高了系统的可用性和可扩展性。
微服务支持： Spring Boot适用于构建微服务架构，与Spring Cloud等相关项目集成，可以快速搭建和部署微服务应用，实现分布式系统的开发和管理。
问题2：为什么使用了Spring的依赖注入和控制反转（IoC）特性？

回答：我们使用了Spring的依赖注入和控制反转（IoC）特性，主要出于以下几个原因：

松耦合： 依赖注入和控制反转可以帮助我们实现松耦合的设计，将组件之间的依赖关系交由Spring容器来管理，降低了组件之间的耦合度，提高了代码的可维护性和可测试性。
简化配置： 通过依赖注入和控制反转，Spring容器可以自动管理对象的生命周期和依赖关系，减少了手动配置的工作量，简化了系统的配置和管理。
提高灵活性： 依赖注入和控制反转使得系统更加灵活，可以方便地替换和切换实现类，满足不同场景下的需求，同时也便于单元测试和集成测试的编写。
问题3：Spring MVC是如何支持RESTful风格的控制器？

回答：Spring MVC通过 @RequestMapping 注解和 @RestController 注解来支持RESTful风格的控制器。具体来说：

@RequestMapping 注解用于将HTTP请求映射到控制器的处理方法上，并指定URL路径。
@RestController 注解用于标识控制器类，表示这是一个RESTful风格的控制器，返回的数据会以JSON格式进行序列化。
通过这两个注解的配合，Spring MVC能够很方便地实现RESTful风格的控制器，处理HTTP请求，并返回相应的JSON数据。

问题4：为什么选择了MySQL作为后端数据库？

回答：我们选择MySQL作为后端数据库的原因有以下几点：

成熟稳定： MySQL是一种成熟稳定的关系型数据库管理系统，被广泛应用于各种规模的应用程序中，具有良好的稳定性和可靠性。
性能优秀： MySQL在处理大规模数据和高并发访问时表现优秀，具有高性能和高可用性的特点，能够满足我们项目的需求。
开源免费： MySQL是一个开源免费的数据库管理系统，可以免费使用，并且有大量的社区支持和丰富的文档资源，便于开发人员学习和使用。
与Spring集成： MySQL与Spring Framework具有良好的集成性，Spring提供了丰富的支持来简化与MySQL数据库的交互和操作，使得开发工作更加便捷高效。
问题5：在Spring Boot中，如何配置允许跨域请求？

回答：在Spring Boot中，可以通过 @CrossOrigin 注解来配置允许跨域请求。具体来说：

在需要处理跨域请求的控制器类或者方法上使用 @CrossOrigin 注解。
可以通过设置注解的属性来指定允许跨域请求的来源、允许的HTTP方法、允许的头部信息等。
例如，可以在控制器类上添加 @CrossOrigin(origins = "*") 注解，表示允许所有来源的请求跨域访问。也可以根据实际需求进行更精确的配置，如指定具体的来源或者允许的HTTP方法。这样就能够在Spring Boot应用中实现跨域请求的处理。







*/