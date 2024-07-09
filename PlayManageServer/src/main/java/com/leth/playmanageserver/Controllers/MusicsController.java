package com.leth.playmanageserver.Controllers;

import com.leth.playmanageserver.Configs.ResultJson;
import com.leth.playmanageserver.Pojos.Musics;
import com.leth.playmanageserver.Services.MusicsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/*这些语句导入了代码中需要使用的类和包。其中：

ResultJson 是一个用于封装返回结果的类；
Musics 是一个音乐对象的POJO类；
MusicsServices 是处理音乐相关业务逻辑的服务类；
@Autowired 注解用于进行依赖注入；
@CrossOrigin 注解允许跨域请求；
@RequestMapping 和 @RestController 注解用于定义RESTful风格的控制器；
MultipartFile 是Spring框架中用于接收上传文件的类。*/
@RestController
@CrossOrigin(origins = "*")
public class MusicsController {
    @Autowired //注入service对象
    private MusicsServices musicsServices;

    /*这段代码定义了一个名为 MusicsController 的控制器类，并使用 @RestController 注解表示这是一个RESTful风格的控制器。@CrossOrigin(origins = "*") 注解表示允许跨域请求。接着通过 @Autowired 注解注入了一个 MusicsServices 类的实例。

在这段代码中，我们定义了一个控制器类，并且注入了一个服务类的实例，准备在该控制器中处理与音乐相关的业务逻辑。*/

    @RequestMapping("insertMusics")
    public ResultJson insertMusics(Musics musics) {
        int result = musicsServices.insertMusics(musics);
        if(result == 1){
            return ResultJson.success();
        }else{
            return ResultJson.error();
        }
    }
/*这个方法接受一个 Musics 对象作为参数，表示要插入的音乐信息。在方法体内，首先调用了 musicsServices.insertMusics(musics) 方法来执行实际的插入操作，返回一个表示插入结果的整数值。

如果插入成功（即 result == 1），则返回一个表示成功的 ResultJson 对象，通过调用 ResultJson.success() 方法创建。否则，返回一个表示失败的 ResultJson 对象，通过调用 ResultJson.error() 方法创建。

这段代码的作用是将传入的音乐信息插入到数据库中，并根据插入结果返回相应的成功或失败信息给调用者。*/
    @RequestMapping("deleteMusics")
    /*这段代码定义了一个用于处理删除音乐信息的请求的方法，通过 @RequestMapping("deleteMusics") 注解将该方法映射到路径 "/deleteMusics" 上。*/
    public ResultJson deleteMusics(int id) {
        int result = musicsServices.deleteMusics(id);
        if(result == 1){
            return ResultJson.success();
        }else{
            return ResultJson.error();
        }
    }
/*这个方法接受一个整数类型的参数 id，表示要删除的音乐的标识符。在方法体内，首先调用了 musicsServices.deleteMusics(id) 方法来执行实际的删除操作，返回一个表示删除结果的整数值。

如果删除成功（即 result == 1），则返回一个表示成功的 ResultJson 对象，通过调用 ResultJson.success() 方法创建。否则，返回一个表示失败的 ResultJson 对象，通过调用 ResultJson.error() 方法创建。

这段代码的作用是根据传入的音乐ID从数据库中删除对应的音乐信息，并根据删除结果返回相应的成功或失败信息给调用者。*/
    @RequestMapping("updateMusics")
    /*这段代码定义了一个用于处理更新音乐信息的请求的方法，通过 @RequestMapping("updateMusics") 注解将该方法映射到路径 "/updateMusics" 上。*/
    public ResultJson updateMusics(Musics musics) {
        int result = musicsServices.updateMusics(musics);
        if(result == 1){
            return ResultJson.success();
        }else{
            return ResultJson.error();
        }
    }
/*这个方法接受一个 Musics 对象作为参数，表示要更新的音乐信息。在方法体内，首先调用了 musicsServices.updateMusics(musics) 方法来执行实际的更新操作，返回一个表示更新结果的整数值。

如果更新成功（即 result == 1），则返回一个表示成功的 ResultJson 对象，通过调用 ResultJson.success() 方法创建。否则，返回一个表示失败的 ResultJson 对象，通过调用 ResultJson.error() 方法创建。

这段代码的作用是根据传入的音乐信息更新数据库中对应的音乐信息，并根据更新结果返回相应的成功或失败信息给调用者。*/
    @RequestMapping("listMusicsAll")
    /*这段代码定义了一个用于获取所有音乐信息的方法，通过 @RequestMapping("listMusicsAll") 注解将该方法映射到路径 "/listMusicsAll" 上。*/
    public ResultJson listMusicsAll() {
        List<Musics> result = musicsServices.listMusicsAll();
        if(!result.isEmpty()){
            return ResultJson.success(result);
        }else{
            return ResultJson.error();
        }
    }
/*该方法不接受任何参数。在方法体内，首先调用了 musicsServices.listMusicsAll() 方法来获取所有音乐的列表。然后，通过判断结果列表是否为空，如果不为空，则返回一个表示成功并携带音乐列表的 ResultJson 对象，通过调用 ResultJson.success(result) 方法创建。否则，返回一个表示失败的 ResultJson 对象，通过调用 ResultJson.error() 方法创建。

这段代码的作用是从数据库中获取所有音乐信息，并根据获取结果返回相应的成功或失败信息给调用者。*/
    @RequestMapping("searchMusics")
    /*这段代码定义了一个用于搜索符合条件的音乐信息的方法，通过 @RequestMapping("searchMusics") 注解将该方法映射到路径 "/searchMusics" 上。*/
    public ResultJson searchMusics(Musics musics) {
        List<Musics> result = musicsServices.searchMusics(musics);
        if(!result.isEmpty()){
            return ResultJson.success(result);
        }else{
            return ResultJson.error();
        }
    }
/*这个方法接受一个 Musics 对象作为参数，表示搜索请求中提供的音乐信息条件。在方法体内，首先调用了 musicsServices.searchMusics(musics) 方法来执行实际的搜索操作，返回一个表示搜索结果的音乐列表。

然后，通过判断结果列表是否为空，如果不为空，则表示搜索到了符合条件的音乐，返回一个表示成功并携带音乐列表的 ResultJson 对象，通过调用 ResultJson.success(result) 方法创建。否则，返回一个表示搜索失败的 ResultJson 对象，通过调用 ResultJson.error() 方法创建。

这段代码的作用是根据传入的音乐信息条件进行搜索，并根据搜索结果返回相应的成功或失败信息给调用者。*/
    @RequestMapping("searchMusicsOne")
    /*这段代码定义了一个用于根据音乐ID搜索音乐信息的方法，通过 @RequestMapping("searchMusicsOne") 注解将该方法映射到路径 "/searchMusicsOne" 上。*/
    public ResultJson searchMusicsOne(int id) {
        List<Musics> result = musicsServices.searchMusicsOne(id);
        if(!result.isEmpty()){
            return ResultJson.success(result);
        }else{
            return ResultJson.error();
        }
    }
/*这个方法接受一个整数类型的参数 id，表示要搜索的音乐的标识符。在方法体内，首先调用了 musicsServices.searchMusicsOne(id) 方法来执行实际的搜索操作，返回一个表示搜索结果的音乐列表。

然后，通过判断结果列表是否为空，如果不为空，则表示搜索到了符合条件的音乐，返回一个表示成功并携带音乐列表的 ResultJson 对象，通过调用 ResultJson.success(result) 方法创建。否则，返回一个表示搜索失败的 ResultJson 对象，通过调用 ResultJson.error() 方法创建。

这段代码的作用是根据传入的音乐ID从数据库中获取对应的音乐信息，并根据获取结果返回相应的成功或失败信息给调用者。*/

    @RequestMapping("uploadImg")
    /*这段代码定义了一个用于上传图片文件的方法，通过 @RequestMapping("uploadImg") 注解将该方法映射到路径 "/uploadImg" 上。*/
    public ResultJson uploadImg(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return ResultJson.success("Fail!");
        }
        long timestamp = System.currentTimeMillis();
        String name = timestamp+multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        File file = new File("D:\\xampp2024\\htdocs\\dashboard\\project\\tmp\\"+name);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        multipartFile.transferTo(file);
        return ResultJson.success(name);
    }
/*这个方法接受一个 MultipartFile 对象作为参数，表示要上传的图片文件。在方法体内，首先通过 multipartFile.isEmpty() 方法检查上传的文件是否为空，如果为空，则返回一个表示失败的 ResultJson 对象，通过调用 ResultJson.success("Fail!") 方法创建。

然后，生成一个唯一的文件名，将当前时间戳和原始文件名的后缀组合起来作为文件名，并将文件保存在指定的路径下。

最后，返回一个表示成功的 ResultJson 对象，通过调用 ResultJson.success(name) 方法创建，其中 name 是上传的文件名。

这段代码的作用是接受前端上传的图片文件，将其保存到指定路径，并返回上传成功的文件名给前端。*/
    @RequestMapping("uploadMusic")
    /*这段代码定义了一个用于上传音乐文件的方法，通过 @RequestMapping("uploadMusic") 注解将该方法映射到路径 "/uploadMusic" 上。*/
    public ResultJson uploadMusic(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return ResultJson.success("Fail!");
        }
        long timestamp = System.currentTimeMillis();
        String name = timestamp+multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        File file = new File("D:\\xampp2024\\htdocs\\dashboard\\project\\tmp\\"+name);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        multipartFile.transferTo(file);
        return ResultJson.success(name);
    }
/*这个方法接受一个 MultipartFile 对象作为参数，表示要上传的音乐文件。在方法体内，首先通过 multipartFile.isEmpty() 方法检查上传的文件是否为空，如果为空，则返回一个表示失败的 ResultJson 对象，通过调用 ResultJson.success("Fail!") 方法创建。

然后，生成一个唯一的文件名，将当前时间戳和原始文件名的后缀组合起来作为文件名，并将文件保存在指定的路径下。

最后，返回一个表示成功的 ResultJson 对象，通过调用 ResultJson.success(name) 方法创建，其中 name 是上传的文件名。

这段代码的作用是接受前端上传的音乐文件，将其保存到指定路径，并返回上传成功的文件名给前端。*/
    @RequestMapping("uploadLyrc")
    /*这段代码定义了一个用于上传歌词文件的方法，通过 @RequestMapping("uploadLyrc") 注解将该方法映射到路径 "/uploadLyrc" 上。*/
    public ResultJson uploadLyrc(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return ResultJson.success("Fail!");
        }
        long timestamp = System.currentTimeMillis();
        String name = timestamp+multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        File file = new File("D:\\xampp2024\\htdocs\\dashboard\\project\\tmp\\"+name);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        multipartFile.transferTo(file);
        return ResultJson.success(name);
    }
}
/*这个方法接受一个 MultipartFile 对象作为参数，表示要上传的歌词文件。在方法体内，首先通过 multipartFile.isEmpty() 方法检查上传的文件是否为空，如果为空，则返回一个表示失败的 ResultJson 对象，通过调用 ResultJson.success("Fail!") 方法创建。

然后，生成一个唯一的文件名，将当前时间戳和原始文件名的后缀组合起来作为文件名，并将文件保存在指定的路径下。

最后，返回一个表示成功的 ResultJson 对象，通过调用 ResultJson.success(name) 方法创建，其中 name 是上传的文件名。

这段代码的作用是接受前端上传的歌词文件，将其保存到指定路径，并返回上传成功的文件名给前端。*/

/*这段代码实现了一个音乐管理系统的后端控制器，其中包含了一系列用于处理音乐信息的方法，以及上传音乐、图片和歌词文件的方法。下面是功能实现和数据交互的描述：

音乐信息管理功能：

insertMusics 方法用于插入音乐信息到数据库中。
deleteMusics 方法用于根据音乐ID从数据库中删除对应的音乐信息。
updateMusics 方法用于更新数据库中的音乐信息。
listMusicsAll 方法用于获取数据库中所有的音乐信息。
searchMusics 方法用于根据指定条件搜索符合条件的音乐信息。
searchMusicsOne 方法用于根据音乐ID获取对应的音乐信息。
文件上传功能：

uploadImg 方法用于上传图片文件，并将文件保存到指定路径。
uploadMusic 方法用于上传音乐文件，并将文件保存到指定路径。
uploadLyrc 方法用于上传歌词文件，并将文件保存到指定路径。
数据交互：

客户端通过发送HTTP请求到对应的URL路径，如 "/insertMusics"、"/deleteMusics" 等，来触发相应的方法执行。
方法内部调用了对应的服务类方法，如 musicsServices.insertMusics()、musicsServices.deleteMusics() 等，来实现对数据库的增删改查操作。
在文件上传方法中，通过 @RequestParam 注解获取前端传递的文件，并通过 MultipartFile 类处理文件内容，最终将文件保存到指定路径。
这段代码实现了一个基于Spring Boot框架的RESTful风格的后端服务，提供了对音乐信息的管理功能，并支持文件上传操作，实现了前后端的数据交互。

*/

/*技术层面分析：

Spring框架： 这段代码基于Spring框架开发，利用了Spring Boot提供的便捷开发方式和强大的功能，如依赖注入、控制反转、面向切面编程等。

RESTful风格： 控制器类使用了 @RestController 注解，方法通过 @RequestMapping 注解映射HTTP请求路径，实现了RESTful风格的API设计，提供了清晰的接口和资源操作方式。

文件上传： 使用了Spring的 MultipartFile 类处理文件上传功能，并通过 transferTo() 方法将文件保存到指定路径。这种方式可以有效地处理文件上传过程，并提供了灵活的配置选项。

数据交互： 控制器类通过调用服务层方法实现与数据库的交互，实现了对音乐信息的增删改查功能。同时，通过返回不同的 ResultJson 对象表示操作结果，实现了与前端的数据交互。*/


/*在文件上传方法中，为什么选择了将文件保存到指定路径，而不是保存到数据库中？
保存文件到指定路径是一种常见的做法，因为：

将文件保存到文件系统中可以减少数据库的负担，特别是对于大文件而言，存储在文件系统中更加高效。
文件系统更适合存储大量的静态资源文件，如图片、音乐等，而数据库更适合存储结构化数据。
在文件系统中存储文件可以方便地进行备份和恢复，也更容易与其他系统进行集成。
这段代码中是否考虑了文件上传过程中可能出现的异常情况，如文件为空或保存失败等？如果有的话，是如何处理的？
是的，代码中通过以下方式处理了文件上传过程中可能出现的异常情况：

首先通过 multipartFile.isEmpty() 方法检查上传的文件是否为空，如果为空，则返回一个表示失败的结果，告知上传失败。
对于文件保存过程中可能出现的异常情况，如文件保存失败或IO异常等，Spring框架会抛出相应的异常。在这种情况下，可以通过异常处理机制（如try-catch块）来捕获异常，并返回相应的错误信息给客户端。
控制器类中的方法是否考虑了请求参数的校验和异常情况的处理？
是的，控制器类中的方法应该考虑了请求参数的校验和异常情况的处理：

通过Spring框架提供的注解（如 @RequestParam）可以对请求参数进行校验，例如检查参数是否为空、是否符合特定的格式要求等。
对于异常情况的处理，可以使用异常处理器来统一处理控制器方法中可能抛出的异常，或者在方法内部通过try-catch块捕获并处理异常，以确保系统的稳定性和健壮性。*/