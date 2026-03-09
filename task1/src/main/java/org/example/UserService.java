package org.example;
//task 1
//public class UserService {
//    private UserRepository userRepository;
//
//    public UserService(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }
//
//    public void processUser(){
//        userRepository.getUser();
//        System.out.println("Processing user in service layer");
//    }
//}


//task 3
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;
//
//public class UserService implements InitializingBean, DisposableBean {
//
//    private UserRepository repo;
//
//    // 1. Creation
//    public UserService(UserRepository repo) {
//        this.repo = repo;
//        System.out.println("1. Bean Created: UserService Constructor");
//    }
//
//    public void processUser() {
//        repo.getUser();
//        System.out.println("Business logic executed");
//    }
//
//    // 3. After Dependency Injection
//    @PostConstruct
//    public void postConstruct() {
//        System.out.println("3. @PostConstruct: Dependencies Injected");
//    }
//
//    // 4. Initialization Stage
//    @Override
//    public void afterPropertiesSet() {
//        System.out.println("4. InitializingBean.afterPropertiesSet(): Bean Initialized");
//    }
//
//    // 6. Before destruction
//    @PreDestroy
//    public void preDestroy() {
//        System.out.println("6. @PreDestroy: Bean about to be destroyed");
//    }
//
//    // 7. Destruction
//    @Override
//    public void destroy() {
//        System.out.println("7. DisposableBean.destroy(): Bean destroyed");
//    }
//
//}
