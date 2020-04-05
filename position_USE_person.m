clear;
close all;

A = readtable('Position.txt'); %reads in text file
B = importdata('Position.txt');
K = size(B.textdata);          %used to check how many people in code
steps = size(A(:,1));          %checks how many steps where taken in code
t = 0:(10/(steps(1) -1)):10;   %last number here represent total time open in hours, has to be same as in simulation!
%%   wonky 3d
for i = 1:(K(2)/2)
    k = -1+i*2;
    l = i*2;
    figure(i)
    plot3(A{:,k},A{:,l},t)
    
    xlabel('X-position');
    ylabel('Y-position');
    zlabel('Time [Hour]')
    grid on;
%     xlabel('Time [hour]');
%     ylabel('Position');
end
