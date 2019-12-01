package br.com.barroso.sample.redis.sample.redis.domain

import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity( name = "customer" )
class Customer(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long,

        @NotNull(message = "Field: Customer.name is mandatory!")
        @Column( nullable = false )
        var name: String,

        @NotNull(message = "Field: Customer.lastName is mandatory!")
        @Column( name = "last_name", nullable = false )
        var lastName: String,

        @NotNull(message = "Field: Customer.phoneNumber is mandatory!")
        @Column( name = "phone_number", nullable = false )
        var phoneNumber: String,

        @NotNull(message = "Field: Customer.userName is mandatory!")
        @Column( name = "user_name", nullable = false, unique = true )
        var userName: String,

        @NotNull(message = "Field: Customer.password is mandatory!")
        @Column( nullable = false )
        var password: String ) : Serializable {

        companion object {
                const val CACHE_NAME: String = "Customer"
        }
}
