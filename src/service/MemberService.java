package service;

import model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages library members.
 * Demonstrates: Lambdas, Streams, Encapsulation
 */
public class MemberService {

    private final List<Member> members;

    public MemberService() {
        this.members = new ArrayList<>();
    }

    public Member registerMember(String name, String email, String phone) {
        // Check for duplicate email using lambda
        boolean exists = members.stream()
                .anyMatch(m -> m.getEmail().equalsIgnoreCase(email));
        if (exists) {
            System.out.println("  ✘ A member with this email already exists.");
            return null;
        }
        Member member = new Member(name, email, phone);
        members.add(member);
        System.out.println("  ✔ Registered: " + member);
        return member;
    }

    public Member findById(String id) {
        return members.stream()
                .filter(m -> m.getMemberId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public List<Member> searchByName(String keyword) {
        return members.stream()
                .filter(m -> m.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void printAllMembers() {
        if (members.isEmpty()) {
            System.out.println("  No members registered.");
            return;
        }
        members.forEach(m -> System.out.printf("  %s | Active borrows: %d%n",
                m, m.getActiveBorrowCount()));
    }

    public List<Member> getAllMembers() { return members; }
}
