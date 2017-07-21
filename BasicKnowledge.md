1. 延迟初始化属性 -- lateinit
  该修饰符只能用于在类体中声明的 var 属性，并且仅当该属性没有自定义 getter 或 setter 时。该属性必须是非空类型，并且不能是原声类型。
  在初始化前访问一个 lateinit 属性会抛出一个特定异常，该异常明确标识该属性被访问及它没有初始化的事实。
  The modifier can only be used on var properties declared inside the body of a class「not in the peimary constructor」,
and only when the property does not have a costom getter or setter. The type of the property must be non-null ,and it 
must not be a primitive type.
  Accessing a lateinit property before it has been initialized throws a special exception that clearly identifies the 
property being accessed and the fact that it hasn't been initialized.
