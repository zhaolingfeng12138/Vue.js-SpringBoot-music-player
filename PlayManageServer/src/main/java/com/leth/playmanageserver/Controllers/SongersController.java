package com.leth.playmanageserver.Controllers;

import com.leth.playmanageserver.Configs.ResultJson;
import com.leth.playmanageserver.Pojos.Songers;
import com.leth.playmanageserver.Services.SongersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class SongersController {
    @Autowired //注入service对象
    private SongersServices songersServices;

    @RequestMapping("insertSongers")
    public ResultJson insertSongers(Songers songers) {
        int result = songersServices.insertSongers(songers);
        if(result == 1){
            return ResultJson.success();
        }else{
            return ResultJson.error();
        }
    }

    @RequestMapping("deleteSongers")
    public ResultJson deleteSongers(int id) {
        int result = songersServices.deleteSongers(id);
        if(result == 1){
            return ResultJson.success();
        }else{
            return ResultJson.error();
        }
    }

    @RequestMapping("updateSongers")
    public ResultJson updateSongers(Songers songers) {
        int result = songersServices.updateSongers(songers);
        if(result == 1){
            return ResultJson.success();
        }else{
            return ResultJson.error();
        }
    }

    @RequestMapping("listSongersAll")
    public ResultJson listSongersAll() {
        List<Songers> result = songersServices.listSongersAll();
        if(!result.isEmpty()){
            return ResultJson.success(result);
        }else{
            return ResultJson.error();
        }
    }

    @RequestMapping("searchSongers")
    public ResultJson searchSongers(Songers songers) {
        List<Songers> result = songersServices.searchSongers(songers);
        if(!result.isEmpty()){
            return ResultJson.success(result);
        }else{
            return ResultJson.error();
        }
    }

    @RequestMapping("searchSongersOne")
    public ResultJson searchSongersOne(int id) {
        List<Songers> result = songersServices.searchSongersOne(id);
        if(!result.isEmpty()){
            return ResultJson.success(result);
        }else{
            return ResultJson.error();
        }
    }
}

/*这段代码是一个Java的REST控制器，用于处理"Songers"实体的CRUD操作（创建、读取、更新、删除）。它使用了Spring Boot注解来定义REST端点、依赖注入和映射。它旨在与服务层（SongersServices）集成，执行业务逻辑并与数据库交互。*/

/*这段代码的特定方面包括：

REST端点定义： 通过@RequestMapping注解定义了多个REST端点，包括插入、删除、更新、获取全部、搜索和根据ID查询Songers信息的端点。

依赖注入： 使用@Autowired注解将SongersServices服务注入到控制器中，以便在控制器中调用服务层的方法。

业务逻辑： 控制器中的每个方法都调用了相应的服务层方法来执行实际的业务逻辑，例如插入、删除、更新、查询等操作。

异常处理： 每个方法中都包含了对操作结果的判断，并根据结果返回相应的ResultJson对象表示成功或失败。这种方式可以处理可能出现的异常情况，例如插入失败、查询结果为空等情况。

跨域支持： 使用@CrossOrigin(origins = "*")注解启用了跨域资源共享（CORS），允许跨域访问该REST服务。*/

/*这段代码通过 @RequestMapping 注解定义了多个 REST 端点，用于处理不同的 HTTP 请求，并执行相应的操作。以下是每个端点的功能：

插入 Songers：

路径： "/insertSongers"
方法： POST
功能： 将提供的 Songers 对象插入到数据库中。
参数： 通过请求体传递 Songers 对象。
删除 Songers：

路径： "/deleteSongers"
方法： GET 或 POST
功能： 根据提供的 ID 从数据库中删除对应的 Songers 记录。
参数： 通过查询参数传递 ID。
更新 Songers：

路径： "/updateSongers"
方法： POST
功能： 更新数据库中对应的 Songers 记录。
参数： 通过请求体传递更新后的 Songers 对象。
获取全部 Songers：

路径： "/listSongersAll"
方法： GET
功能： 获取数据库中所有的 Songers 记录。
参数： 无。
搜索 Songers：

路径： "/searchSongers"
方法： POST
功能： 根据提供的条件搜索数据库中符合条件的 Songers 记录。
参数： 通过请求体传递搜索条件。
根据ID查询 Songers：

路径： "/searchSongersOne"
方法： GET 或 POST
功能： 根据提供的 ID 查询数据库中对应的 Songers 记录。
参数： 通过查询参数传递 ID。
这些端点定义了控制器提供的不同功能，客户端可以通过向相应的端点发送 HTTP 请求来执行相应的操作。*/

/*在这段代码中，依赖注入是通过 Spring 框架提供的 @Autowired 注解实现的。具体来说，控制器类 SongersController 中的 SongersServices 类型的字段被标注为 @Autowired，以实现依赖注入。
通过这样的注解，Spring 框架会在初始化 SongersController 实例时，自动将 SongersServices 类型的实例注入到 songersServices 字段中。这样，控制器就可以直接调用 songersServices 对象中的方法，而无需自己创建或管理该对象。

依赖注入的好处在于：

降低了类之间的耦合度，使得代码更加灵活和易于维护。
提高了代码的可测试性，因为可以轻松地替换依赖的实现，例如使用模拟对象进行单元测试。
提高了代码的可读性，因为依赖关系都明确地声明在类的字段上，使得代码结构更清晰。
通过依赖注入，控制器类 SongersController 就可以直接使用 songersServices 实例执行与数据库交互的操作，而不必关心如何创建或配置该实例。*/

/*在这段代码中，控制器类 SongersController 中的每个方法都涉及到业务逻辑的执行。以下是每个方法的业务逻辑概述：

插入 Songers (insertSongers 方法)：

接收一个 Songers 对象作为参数，将其插入到数据库中。
调用 songersServices 中的 insertSongers 方法执行实际的插入操作。
如果插入成功（返回值为1），则返回成功的 ResultJson 对象；否则返回失败的 ResultJson 对象。
删除 Songers (deleteSongers 方法)：

接收一个 ID 作为参数，根据该 ID 从数据库中删除对应的 Songers 记录。
调用 songersServices 中的 deleteSongers 方法执行实际的删除操作。
如果删除成功（返回值为1），则返回成功的 ResultJson 对象；否则返回失败的 ResultJson 对象。
更新 Songers (updateSongers 方法)：

接收一个 Songers 对象作为参数，将其更新到数据库中。
调用 songersServices 中的 updateSongers 方法执行实际的更新操作。
如果更新成功（返回值为1），则返回成功的 ResultJson 对象；否则返回失败的 ResultJson 对象。
获取全部 Songers (listSongersAll 方法)：

从数据库中获取所有的 Songers 记录。
调用 songersServices 中的 listSongersAll 方法执行实际的获取操作。
如果获取到了记录，则返回包含记录的成功的 ResultJson 对象；否则返回失败的 ResultJson 对象。
搜索 Songers (searchSongers 方法)：

接收一个 Songers 对象作为参数，根据指定的条件搜索数据库中符合条件的 Songers 记录。
调用 songersServices 中的 searchSongers 方法执行实际的搜索操作。
如果搜索到了记录，则返回包含记录的成功的 ResultJson 对象；否则返回失败的 ResultJson 对象。
根据ID查询 Songers (searchSongersOne 方法)：

接收一个 ID 作为参数，根据该 ID 查询数据库中对应的 Songers 记录。
调用 songersServices 中的 searchSongersOne 方法执行实际的查询操作。
如果查询到了记录，则返回包含记录的成功的 ResultJson 对象；否则返回失败的 ResultJson 对象。
这些方法实现了与数据库的交互，并通过返回不同的 ResultJson 对象表示操作结果，使得控制器能够根据业务逻辑执行结果向客户端返回相应的信息。*/

/*在这段代码中，异常处理通过控制器中的条件判断来实现。具体来说，每个方法都包含了对操作结果的判断，并根据结果返回相应的 ResultJson 对象表示成功或失败。以下是异常处理的概述：

插入 Songers (insertSongers 方法)：

如果插入成功（返回值为1），则返回成功的 ResultJson 对象；否则返回失败的 ResultJson 对象。
删除 Songers (deleteSongers 方法)：

如果删除成功（返回值为1），则返回成功的 ResultJson 对象；否则返回失败的 ResultJson 对象。
更新 Songers (updateSongers 方法)：

如果更新成功（返回值为1），则返回成功的 ResultJson 对象；否则返回失败的 ResultJson 对象。
获取全部 Songers (listSongersAll 方法)：

如果获取到了记录，则返回包含记录的成功的 ResultJson 对象；否则返回失败的 ResultJson 对象。
搜索 Songers (searchSongers 方法)：

如果搜索到了记录，则返回包含记录的成功的 ResultJson 对象；否则返回失败的 ResultJson 对象。
根据ID查询 Songers (searchSongersOne 方法)：

如果查询到了记录，则返回包含记录的成功的 ResultJson 对象；否则返回失败的 ResultJson 对象。
在这些方法中，通过检查返回的结果值来确定操作是否成功，并相应地返回不同的 ResultJson 对象。如果操作失败，通常返回的 ResultJson 对象中会包含失败的消息或错误代码，以便客户端能够识别和处理异常情况。

这种简单的异常处理方式适用于大多数情况，但在更复杂的情况下可能需要更精细的异常处理逻辑，例如捕获特定类型的异常并采取不同的处理方式。*/

/*在这段代码中，通过 @CrossOrigin(origins = "*") 注解启用了跨域资源共享（CORS）支持。具体来说，@CrossOrigin 注解用于控制器类 SongersController 上，参数 origins = "*" 表示允许来自任何来源的跨域请求。

跨域资源共享（CORS）是一种机制，允许网页应用程序从不同的域访问其资源。在Web开发中，由于浏览器的同源策略，通常情况下，来自不同域的请求会被浏览器阻止。但通过启用 CORS，服务器可以允许来自其他域的请求访问其资源。

在这段代码中，通过设置 @CrossOrigin(origins = "*")，允许了所有来源的请求访问 SongersController 中定义的 REST 端点。这样客户端就可以在不同的域上使用 AJAX 或其他方式发送请求到这些端点，从而实现跨域访问。

需要注意的是，虽然使用 origins = "*" 可以允许来自任何来源的请求，但在实际应用中，建议根据实际情况设置允许访问的域名，以提高安全性。*/