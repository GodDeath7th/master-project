package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.AppRunningManager;
import model.Plan;

public class PlanListController {
	
	private AnchorPane parent;
	
    @FXML
    private ScrollPane planListPane;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label tipLabel;

    @FXML
    private JFXButton newButton;

    @FXML
    private ImageView tipImage;

    @FXML
    private HBox planListBox;


    @FXML
    void addNew(ActionEvent event) throws Exception {
    	parent.getChildren().clear();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewPlan.fxml"));
    	parent.getChildren().add(loader.load());
		NewPlanController controller = loader.getController();
		controller.initData(true, parent);
    }
    
    public void removePlan(Node removeNode) {
    	for(Node node: planListBox.getChildren()) {
    		if(node.equals(removeNode)) {
    			planListBox.getChildren().remove(node);
    			break;
    		}
    	}
    }
    public void setRootContent() throws Exception {
    	parent.getChildren().clear();
    	planListBox.getChildren().clear();
    	ArrayList<Plan> planList;
    	planList = AppRunningManager.getCurrentPlanList();
    	
    	if(planList.size()>0)
    		setTipVisible(false);
    	else {
    		setTipVisible(true);
    		tipLabel.setText("NO PLAN");
    	}
    	for(Plan eachPlan: planList) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Plan.fxml"));
			planListBox.getChildren().add(loader.load());
			PlanController controller = loader.getController();
			controller.initData(eachPlan, this);
		}
    	planListPane.setContent(planListBox);
    	parent.getChildren().add(rootPane);
    }
    
    public void setTipVisible(boolean flag) {
    	tipImage.setVisible(flag);
    	tipLabel.setVisible(flag);
    }
    
    public void initData(AnchorPane parentPane) throws Exception {
    	parent = parentPane;
    	planListBox.setSpacing(20);
    	planListPane.getStylesheets().add(this.getClass().getResource("/css/plan_list_scroll_bar.css").toExternalForm());

    	
    	AppRunningManager.processQuery("Get Plan List", new String[] {AppRunningManager.getCurrentPatient().getId()});
    	
    	setRootContent();
    }   
}