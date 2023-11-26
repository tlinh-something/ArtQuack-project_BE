package com.swp.ArtQuack.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.swp.ArtQuack.entity.*;
import com.swp.ArtQuack.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swp.ArtQuack.view.EnrollmentObject;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepoService;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CourseRepository courseRepository;
    private float price;

    public List<Enrollment> findAll() {
        return enrollmentRepoService.findAll();
    }

    public Enrollment findById(int enrollmentID) {
        Optional<Enrollment> enroll = enrollmentRepoService.findById(enrollmentID);
        if (enroll.isPresent()) return enroll.get();
        else return null;
    }

    public List<Enrollment> findByDate(Date date) {
        return enrollmentRepoService.findByDate(date);
    }

    public List<Enrollment> findByLearnerID(int learnerID) {
        List<Enrollment> ls = new ArrayList<Enrollment>();
        ls.addAll(enrollmentRepoService.findByLearnerLearnerID(learnerID));
        return ls;
    }

    public List<Enrollment> findByCourseID(int courseID) {
        List<Enrollment> ls = new ArrayList<Enrollment>();
        ls.addAll(enrollmentRepoService.findByCourseCourseID(courseID));
        return ls;
    }

    public boolean hasEnrolled(int learnerID, int courseID) {
        return enrollmentRepoService.existsByLearnerLearnerIDAndCourseCourseID(learnerID, courseID);
    }

    	public List<Enrollment> findByCourseIDAndLearnerID(int courseID, int learnerID) {
		return enrollmentRepoService.findByCourseCourseIDAndLearnerLearnerID(courseID, learnerID);
	}

    //ADD
    public Enrollment add(Enrollment enrollment) {
        Enrollment enroll = enrollmentRepoService.save(enrollment);
        float price = enroll.getCourse().getPrice();
        Admin admin = adminRepository.findFirstByAdminIDNotNull();
        Enrollment enroll1 =  enrollmentRepoService.findByEnrollmentIDAndStatusIsTrue(enroll.getEnrollmentID());
        Wallet learnerWallet = walletRepository.findWalletByLearnerLearnerID(enroll.getLearner().getLearnerID());
        Wallet adminWallet = walletRepository.findWalletByAdminAdminID(admin.getAdminID());
        Wallet instuctorWallet = walletRepository.findWalletByInstructorInstructorID(enroll.getCourse().getInstructor().getInstructorID());
        if (learnerWallet == null) {
            Wallet wallet = new Wallet();
            wallet.setLearner(enroll.getLearner());
            wallet.setBalance(0);
            learnerWallet = walletRepository.save(wallet);
        }

        if (adminWallet == null) {
            Wallet wallet = new Wallet();
            wallet.setAdmin(admin);
            wallet.setBalance(0);
            adminWallet = walletRepository.save(wallet);
        }

        if (instuctorWallet == null) {
            Wallet wallet = new Wallet();
            wallet.setInstructor(enroll.getCourse().getInstructor());
            wallet.setBalance(0);
            instuctorWallet = walletRepository.save(wallet);
        }

        Transaction transaction1 = new Transaction();
        transaction1.setDate(new Date());
        transaction1.setToWallet(learnerWallet);
        transaction1.setMoney(price);
        transaction1.setEnrollment(enroll1);
        transactionRepository.save(transaction1);


        Transaction transaction2 = new Transaction();
        transaction2.setDate(new Date());
        transaction2.setFromWallet(learnerWallet);
        transaction2.setToWallet(adminWallet);
        transaction2.setMoney(price);
        transaction2.setEnrollment(enroll1);
        transactionRepository.save(transaction2);

        Transaction transaction3 = new Transaction();
        transaction3.setDate(new Date());
        transaction3.setFromWallet(adminWallet);
        transaction3.setToWallet(instuctorWallet);
        transaction3.setMoney(price * 0.95);
        transaction3.setEnrollment(enroll1);
        transactionRepository.save(transaction3);

        adminWallet.setBalance(adminWallet.getBalance() + price * 0.05);
        instuctorWallet.setBalance(instuctorWallet.getBalance() + price * 0.95);
        enrollmentRepoService.save(enroll1);
        walletRepository.save(adminWallet);
        walletRepository.save(instuctorWallet);

        return enroll;
    }

    //UPDATE
    public Enrollment update(Enrollment newEnroll) {
        return enrollmentRepoService.save(newEnroll);
    }

    //DELETE
    public boolean delete(int id) {
        enrollmentRepoService.deleteById(id);
        return enrollmentRepoService.findById(id).isEmpty();
    }

    //DISPLAY
    public List<EnrollmentObject> display(List<Enrollment> ls) {
        List<EnrollmentObject> list = new ArrayList<EnrollmentObject>();
        for (Enrollment x : ls) {
            EnrollmentObject y = new EnrollmentObject();
            y.setEnrollmentID(x.getEnrollmentID());
            y.setRate(x.getRate());
            y.setComment(x.getComment());
            y.setDate(x.getDate());
            y.setTypeOfReport(x.getTypeOfReport());
            y.setReport(x.getReport());
            y.setStatus(x.isStatus());

            y.setInstructorID(x.getCourse().getInstructor().getInstructorID());
            y.setInstructorName(x.getCourse().getInstructor().getName());

            Course course = x.getCourse();
            y.setCourseID(course.getCourseID());
            y.setName(course.getName());
            y.setDescription(x.getCourse().getDescription());
            y.setRateCourse(x.getCourse().getRate());
            y.setUpload_date(x.getCourse().getUpload_date());
            y.setViewer(x.getCourse().getViewer());
            y.setAvatar(x.getCourse().getAvatar());
            y.setPrice(x.getCourse().getPrice());
            y.setCourseStatus(x.getCourse().getCourseStatus());

            y.setCateID(x.getCourse().getCategory().getCateID());
            y.setCateName(x.getCourse().getCategory().getCateName());
            y.setLevelID(x.getCourse().getLevel().getLevelID());
            y.setLevelName(x.getCourse().getLevel().getLevelName());

            Learner learner = x.getLearner();
            y.setLearnerID(learner.getLearnerID());
            y.setLearnerName(learner.getName());
            list.add(y);
        }
        return list;
    }
}
