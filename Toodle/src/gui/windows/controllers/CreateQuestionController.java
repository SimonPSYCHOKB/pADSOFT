package gui.windows.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import application.Application;

import exercise.Exercise;
import exercise.FreeText;
import exercise.MultipleAnswer;
import exercise.SingleAnswer;
import exercise.TrueFalse;
import gui.CancelController;
import gui.panels.controllers.DeleteTestController;
import gui.windows.CreateMultipleAnswer;
import gui.windows.CreateQuestion;
import gui.windows.CreateSingleAnswer;
import gui.windows.CreateTextQuestion;
import gui.windows.EditTest;
import gui.windows.General;

public class CreateQuestionController implements ActionListener {
	
	
	private CreateQuestion view;
	private Exercise model;
	private EditTest update;
	private Application app;
	private General gen;

	public CreateQuestionController(CreateQuestion view, Exercise model, EditTest update, Application app, General gen){
		this.view = view;
		this.model = model;
		this.update = update;
		this.app = app;
		this.gen = gen;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Question data
		final String wording = view.getWording().getText();
		final double penalty = (double) view.getPenalty().getValue();
		final double weight = (double) view.getWeight().getValue();
		final String type = (String) view.getTypeQuestion().getSelectedItem();
		
		if(type.equals("Multiple Choice")){
			final CreateMultipleAnswer cma = new CreateMultipleAnswer();
			cma.setControllerCancel(new CancelController(cma));
			cma.setControllerDone(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					List<String> options = new ArrayList<String>();
					List<String> answers = new ArrayList<String>();

					List<JCheckBox> answer = cma.getAnswers();
					int i = 0;
					for(JTextField option : cma.getOptions()){
						if(option.getText().equals("")){
							i += 1;
							continue;
						}
						options.add(option.getText());
						if(answer.get(i).isSelected())
							answers.add(option.getText());
						i += 1;
					}
					if(answers.isEmpty()){
						JOptionPane.showMessageDialog(cma, "The options must include the answers", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					model.addQuestion(new MultipleAnswer(wording, weight, penalty, answers, options));
					EditTest et = new EditTest(model, app, gen);
					et.setControllerSave(new EditTestController(model, et));
					et.setControllerDelete(new DeleteTestController(model, app, gen, et));
					et.setControllerCancel(new CancelController(et));
					update.dispose();
					cma.dispose();
					view.dispose();
				}
			});
		}
		else if(type.equals("Single Answer")){
			final CreateSingleAnswer csa = new CreateSingleAnswer();
			csa.setControllerCancel(new CancelController(csa));
			csa.setControllerDone(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					List<String> options = new ArrayList<String>();
					String answers = "";

					List<JRadioButton> answer = csa.getAnswers();
					int i = 0;
					for(JTextField option : csa.getOptions()){
						if(option.getText().equals("")){
							i += 1;
							continue;
						}
						options.add(option.getText());
						if(answer.get(i).isSelected())
							answers = option.getText();
						i += 1;
					}
					if(answers.equals("")){
						JOptionPane.showMessageDialog(csa, "The options must include the answer", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					model.addQuestion(new SingleAnswer(wording, weight, penalty, answers, options));
					EditTest et = new EditTest(model, app, gen);
					et.setControllerSave(new EditTestController(model, et));
					et.setControllerDelete(new DeleteTestController(model, app, gen, et));
					et.setControllerCancel(new CancelController(et));
					update.dispose();
					csa.dispose();
					view.dispose();
				}
			});
		}
		else{
			final CreateTextQuestion ctq = new CreateTextQuestion("Create " + type + " Question");
			ctq.setControllerCancel(new CancelController(ctq));
			ctq.setControllerDone(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String answer = ctq.getAnswer().getText();
					if(answer.equals("")){
						JOptionPane.showMessageDialog(ctq, "The question must have an answer", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(type.equals("Free Text")){
						model.addQuestion(new FreeText(wording, weight, penalty, answer));
					}
					else
						model.addQuestion(new TrueFalse(wording, weight, penalty, answer));
					EditTest et = new EditTest(model, app, gen);
					et.setControllerSave(new EditTestController(model, et));
					et.setControllerDelete(new DeleteTestController(model, app, gen, et));
					et.setControllerCancel(new CancelController(et));
					update.dispose();
					ctq.dispose();
					view.dispose();
				}		
			});
		}
		
		
	}

}
