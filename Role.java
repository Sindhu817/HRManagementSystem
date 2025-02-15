package com.hrmanagement.hrmanagementsystem.entities;
import jakarta.persistence.*;
// Entity annotation marks this class as a JPA entity.
@Entity
// Specifies the database table name associated with this entity.
@Table(name = "Role")
public class Role {
    // Marks this field as the primary key.
    @Id
    // Specifies that the primary key is auto-generated using an identity strategy.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_Id")
    private int roleId;
    // Column for role name, cannot be null, max length is 100 characters.
    @Column(name = "Role_Name", nullable = false, length = 100)
    private String roleName;
    // Column for role description, stored as a text type in the database.
    @Column(name = "Role_Description", nullable = false, columnDefinition = "TEXT")
    private String roleDescription;
    // Column for required skill set, cannot be null, max length is 200 characters.
    @Column(name = "SkillSet", nullable = false, length = 200)
    private String skillSet;
    // Parameterized Constructor to initialize all fields.
    public Role(int roleId, String roleName, String roleDescription, String skillSet) {
        super();
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
        this.skillSet = skillSet;
    }
    // Default Constructor (required by JPA).
    public Role() {
        super();
    }
    // Getter and Setter methods 
    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
    public String getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }
    // Overrides the default toString() method to provide a meaningful string representation.
    @Override
    public String toString() {
        return "\n------------Role Details-------\n" +
               "RoleId          : " + roleId +
               "\nRoleName        : " + roleName +
               "\nRoleDescription : " + roleDescription +
               "\nSkillSet        : " + skillSet +
               "\n-------------------------------------";
    }
}
