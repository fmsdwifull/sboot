package com.liu.sboot.service;

import com.liu.sboot.mapper.PermissionMapper;
import com.liu.sboot.mapper.RoleMapper;
import com.liu.sboot.model.Permission;
import com.liu.sboot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.*;
// fang jing le rongqi ,why invove it? where pointcut?
/*
我们可以从getAttributes(Object o)方法的参数o中提取出当前的请求url，
然后将这个请求url和数据库中查询出来的所有url pattern一一对照，看符合哪一个url pattern，
然后就获取到该url pattern所对应的角色，当然这个角色可能有多个，所以遍历角色，
最后利用SecurityConfig.createList方法来创建一个角色集合
 */
@Service
public class MyInvocationSecurityMetadataSourceService  implements
        FilterInvocationSecurityMetadataSource {

    @Autowired
    private RoleMapper roleMapper;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();


    //这里难道不从数据库，按照指定用户或者角色取值吗,这样写，是构造函数
    //也就是说第一层括弧实际是定义了一个匿名内部类 (Anonymous Inner Class)，第二层括弧实际上是一个实例初始化块 (instance initializer block)，这个块在内部匿名类构造时被执行
    private final Map<String,String> urlRoleMap = new HashMap<String,String>(){{
        put("/public/login","root");
        put("/health","root");
        put("/public/login","admin");
        put("/demo","user");
        put("/open","xxxx");
    }};
//    //和上面的写法一样，但不知道，上面的{{里面能不能写业务逻辑。
    //private final Map<String,String> urlRoleMap = new HashMap<String,String>();
//
//    // 资源权限集合
//    private static Map<String, Collection<ConfigAttribute>> urlRoleMap = null;
//    public void loadResource(){
//        urlRoleMap = new HashMap<String, Collection<ConfigAttribute>>();
//
//        //取得资源与角色列表
//        List<RescAndRole> resourceList = iRescAndRoleService.query();
//        System.out.println(resourceList);
//        for (RescAndRole resource : resourceList) {
//            Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
//            atts.add(new SecurityConfig(resource.getRoleName() ));
//            urlRoleMap.put(resource.getResString(), atts);
//        }
//    }



    /*
    private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

    *
     * 这个例子放在构造方法里初始化url权限数据，我们只要保证在 getAttributes()之前初始好数据就可以了
     *
    public MyInvocationSecurityMetadataSourceService() {
        Map<RequestMatcher, Collection<ConfigAttribute>> map = new HashMap<>();
        AntPathRequestMatcher matcher = new AntPathRequestMatcher("/public/admin/index");
        SecurityConfig config = new SecurityConfig("admin");
        ArrayList<ConfigAttribute> configs = new ArrayList<>();
        configs.add(config);
        map.put(matcher,configs);

        AntPathRequestMatcher matcher2 = new AntPathRequestMatcher("/public/user/index");
        SecurityConfig config2 = new SecurityConfig("user");
        ArrayList<ConfigAttribute> configs2 = new ArrayList<>();
        configs2.add(config2);
        map.put(matcher2,configs2);

        this.requestMap = map;
    }
    */


    //此方法用于鉴权过程中获取当前的请求URL需要哪种权限
    //也就是说，这里返回的是一个角色，根据url返回一个对应的角色，然后呢？
    /*
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
//        String httpMethod = fi.getRequest().getMethod();
        //Map.Entry里有相应的getKey和getValue方法，即JavaBean，让我们能够从一个项中取出Key和Value。
        //把urlRoleMap.entrySet()里面的内容，放到entry里面

        //entrySet()该方法返回值就是这个map中各个键值对映射关系的集合。
       // System.out.print("---------------xxxxxxx-------"+urlRoleMap.entrySet());
        for(Map.Entry<String,String> entry:urlRoleMap.entrySet())
        {
            if(antPathMatcher.match(entry.getKey(),url))
            {

                //这里不是一次就返回了嘛？循环有什么意思？也就是说，只要找到该url对应的角色就可以了,但如果这里角色有多个呢？
                //最后利用SecurityConfig.createList方法来创建一个角色集合。这里只返回了一个角色，是代码不应该这样写吗
                return SecurityConfig.createList(entry.getValue());
            }
        }
        //没有匹配到,默认是要登录才能访问
        return SecurityConfig.createList("ROLE_USER");

    }
    */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取当前的URL地址
        System.out.println("object的类型为:" + object.getClass());
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        String httpMethod = fi.getRequest().getMethod();
        List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();

        List<Role>  roles = roleMapper.getRoleByUrl(url);
        // Lookup your database (or other source) using this information and populate the
        // list of attributes
        for (Role r : roles) {
            attributes.add(new SecurityConfig(r.getRoleName()));
        }
        //防止数据库中没有数据，不能进行权限拦截
        if(attributes.size()<1){
          //  ConfigAttribute configAttribute = new SecurityConfig("ROLE_NO_USER");
           // attributes.add(configAttribute);
        }
        return attributes;
    }

    /*
    public List<ConfigAttribute> getAttributes(Object object) {
      FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        String httpMethod = fi.getRequest().getMethod();
        List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();

        // Lookup your database (or other source) using this information and populate the
        // list of attributes
        attributes.add(new SecurityConfig("ROLE_admin"));
        attributes.add(new SecurityConfig("ROLE_user"));
        attributes.add(new SecurityConfig("ROLE_ANONYMOUS"));

        return attributes;
        //return null;
    }*/

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

}