package BTOManagementSystem.Model.DAO.Enum;

/**
 * Enumeration representing the various statuses an application can have
 * in the BTO Management System.
 *
 * <p>Status terms:</p>
 * <ul>
 *   <li><b>NA</b> – Not applicable or not yet set</li>
 *   <li><b>PENDING</b> – Application has been submitted and is awaiting processing</li>
 *   <li><b>SUCCESSFUL</b> – Application has been approved</li>
 *   <li><b>UNSUCCESSFUL</b> – Application was rejected or withdrawn</li>
 *   <li><b>BOOKED</b> – Applicant has successfully booked a flat</li>
 * </ul>
 */
public enum ApplicationStatus {
    NA,
    PENDING,
    SUCCESSFUL,
    UNSUCCESSFUL,
    BOOKED
}
// DO NOT TOUCH THIS