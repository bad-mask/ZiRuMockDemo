1. 延迟初始化属性 -- lateinit

  该修饰符只能用于在类体中声明的 var 属性，并且仅当该属性没有自定义 getter 或 setter 时。该属性必须是非空类型，并且不能是原声类型。
  在初始化前访问一个 lateinit 属性会抛出一个特定异常，该异常明确标识该属性被访问及它没有初始化的事实。
  
  The modifier can only be used on var properties declared inside the body of a class「not in the peimary constructor」,
and only when the property does not have a costom getter or setter. The type of the property must be non-null ,and it 
must not be a primitive type.
  Accessing a lateinit property before it has been initialized throws a special exception that clearly identifies the 
property being accessed and the fact that it hasn't been initialized.

2. 构造函数

	Kotlin 中所有的构造函数默认是 public 的。要指定一个类的主构造函数的可见性，需要添加一个显示的 constructor 关键字。

3. 密封类

	密封类用来表示受限的类继承结构：当一个值为有限集中的类型，而不能有任何其他类型时。

4. 枚举类

	枚举类最基本的用法是实现类型安全的枚举。每个枚举常量都是一个对象。枚举常量用逗号分隔。

5. 继承

	如果该类有一个主构造函数，其基类型必须用基类型的主构造参数就地初始化。




