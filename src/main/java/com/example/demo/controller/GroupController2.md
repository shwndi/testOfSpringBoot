#在学习springMvc的过程中要拦截请求，校验数据，因此用到了校验框架，并进行了比较。

1.比较

@Valid是使用hibernate validation的时候使用

@Validated是使用spring Validator校验机制使用

2.实现

java的jsr303声明了@valid的接口，hibernate-validator对其进行了实现

3.依赖

在使用maven框架整合时，需要引入的依赖部分如下：

<span>        

<dependency>              

<groupId>javax.validation</groupId>              

<artifactId>validation-api</artifactId>              

<version>1.1.0.Final</version>          

</dependency>              

<dependency>              

<groupId>org.hibernate</groupId>              

<artifactId>hibernate-validator</artifactId>              

<version>5.2.1.Final</version>          

</dependency>

</span>  

4.JSR303定义的校验类型

4.1空检查

@Null :验证对象是否为null

@NotNull :验证对象是否不为null, 无法查检长度为0的字符串

@NotBlank :检查约束字符串是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.

@NotEmpty :检查约束元素是否为NULL或者是EMPTY.4.2 Boolean检查

@AssertTrue :验证 Boolean 对象是否为 true  

@AssertFalse :验证 Boolean 对象是否为 false  

4.3长度检查

@Size(min=, max=) :验证对象（Array,Collection,Map,String）长度是否在给定的范围之内  @Length(min=, max=) :验证定义的字符串长度是否在给定范围之内4.4日期检查

@Past :验证 Date 和 Calendar 对象是否在当前时间之前  

@Future :验证 Date 和 Calendar 对象是否在当前时间之后

@Pattern :验证 String 对象是否符合正则表达式的规则

4.5数值检查

建议使用在Stirng,Integer类型，不建议使用在int类型上，因为表单值为“”时无法转换为int，但可以转换为Stirng为"",Integer为null.

@Min :验证 Number 和 String 对象是否大等于指定的值  

@Max :验证 Number 和 String 对象是否小等于指定的值  

@DecimalMax :被标注的值必须不大于约束中指定的最大值. 这个约束的参数是一个通过BigDecimal定义的最大值的字符串表示.小数存在精度

@DecimalMin :被标注的值必须不小于约束中指定的最小值. 这个约束的参数是一个通过BigDecimal定义的最小值的字符串表示.小数存在精度

@Digits:验证 Number 和 String 的构成是否合法  @Digits(integer=,fraction=) :验证字符串是否是符合指定格式的数字，interger指定整数精度，fraction指定小数精度。@Range(min=, max=) :验证定义的值是否在范围之内@Range(min=10000,max=50000,message="range.bean.wage")private BigDecimal wage;@Valid ：递归的对关联对象进行校验, 如果关联对象是个集合或者数组,那么对其中的元素进行递归校验,如果是一个map,则对其中的值部分进行校验.(是否进行递归验证)@CreditCardNumber：信用卡验证@Email ：验证是否是邮件地址，如果为null,不进行验证，算通过验证。@ScriptAssert(lang= ,script=, alias=)@URL(protocol=,host=, port=,regexp=, flags=)

5.使用

5.1新建一个类

/**

 *

 * @Title: AppConfigList.java

 * @Package com.browser.model

 * @Description: 白名单Url匹配规则类

 * @author phycho

 * @date 2017/6/26 下午4:45:30

 * @version V1.0

 */

public class AppConfigList extends Page<AppConfigList> {

    private Integer id;//Id

    @NotNull(message = "appId不能为空", groups = {AccountGroup.Update.class})

    private Integer appId;// 应用id

    @NotEmpty(message = "规则不能为空", groups = {AppConfigGroup.Add.class,AppConfigGroup.Update.class})

    @Size(max = 200,groups= {AppConfigGroup.Add.class,AppConfigGroup.Update.class})

    private String url;// url规则

    @NotNull(message = "typec不能为空", groups = {AccountGroup.Update.class})

    private Byte typec = 1;// 类型

    private Date createTime;// 创建时间

    private Date modifTime;// 更新时间

    @NotNull(message = "状态参数不能为空", groups = {AppConfigGroup.Update.class})

    private Byte status = 1;// 状态

    private Double orders;// 字段顺序

    /**

     * 生成get、set方法

     *

     * @return

     */

}

5.2校验逻辑

/**

     * @param result 绑定结果

     * @param model 数据模型

     * @return 是否发生错误（ true：发生错误，false:未发生错误）

     * @Description: 后台校验

     */

 public static boolean validate(BindingResult result, Model model) {

     boolean validateResult = false;

     if (result.hasErrors()) {

      validateResult = true;

         if (model != null) {

         FieldError error = result.getFieldErrors().get(0);

// 为了避免大量的校验在前端堆积,影响用户体验，只返回一个错误提示

          log.debug("validate error: " + error.getDefaultMessage());

          model.addAttribute("msg", error.getDefaultMessage());

        }

      }

      return validateResult;

  }

5.3 Controller中的使用

// 添加白名单规则

    @RequestMapping(value = "/page/add", method = RequestMethod.POST)

    @ResponseBody

    public ResultData addUrlRule(@Validated(AppConfigGroup.Add.class) AppConfigList appConfigList, BindingResult result, Model model) {

        // 后台校验

        if (ValidateHandler.validate(result, model)) {

            return null;

        }

        appConfigList.setStatus((byte) 1);

        appConfigList.setCreateTime(new Date());

        appConfigList.setModifTime(new Date());

        Double orders = appConfigService.selectOrdersByAppId(appConfigList.getAppId());

        if (orders == null) {

            appConfigList.setOrders((double) 1000);

        } else {

            appConfigList.setOrders(orders + 1000);

        }

        return getResult(appConfigService.insert(appConfigList));

}

6.异常

@NotNull 和 @NotEmpty  和@NotBlank 区别

@NotEmpty 用在集合类上面

@NotBlank 用在String上面

@NotNull  用在基本类型上

如果在基本类型上面用NotEmpty或者NotBlank 会出现下面的错误

SEVERE: Servlet.service() for servlet springmvc threw exception  javax.validation.UnexpectedTypeException: No validator could be found for type: java.lang.Integer      at org.hibernate.validator.engine.ConstraintTree.verifyResolveWasUnique(ConstraintTree.java:383)      at org.hibernate.validator.engine.ConstraintTree.findMatchingValidatorClass(ConstraintTree.java:364)      at org.hibernate.validator.engine.ConstraintTree.getInitializedValidator(ConstraintTree.java:313)      at org.hibernate.validator.engine.ConstraintTree.validateConstraints(ConstraintTree.java:144)      at org.hibernate.validator.engine.ConstraintTree.validateComposingConstraints(ConstraintTree.java:233)      at org.hibernate.validator.engine.ConstraintTree.validateConstraints(ConstraintTree.java:128)      at org.hibernate.validator.engine.ConstraintTree.validateConstraints(ConstraintTree.java:117)      at org.hibernate.validator.metadata.MetaConstraint.validateConstraint(MetaConstraint.java:84)      at org.hibernate.validator.engine.ValidatorImpl.validateConstraint(ValidatorImpl.java:452)  

 